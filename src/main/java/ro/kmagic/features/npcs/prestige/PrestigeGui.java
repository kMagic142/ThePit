package ro.kmagic.features.npcs.prestige;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.database.Database;
import ro.kmagic.libapi.gui.ClickAction;
import ro.kmagic.libapi.gui.GUI;
import ro.kmagic.libapi.gui.GuiItem;
import ro.kmagic.libapi.versionsupport.VersionSupport;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.Main;
import ro.kmagic.api.events.player.PlayerPrestigeEvent;
import ro.kmagic.features.chatoption.ChatOption;
import ro.kmagic.features.chatoption.PlayerChatOption;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.features.economy.renown.RenownEconomy;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.perk.PlayerPerk;
import ro.kmagic.features.prestige.PlayerPrestige;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PrestigeGui {
    private final List<String> cachedPrestigeLore;
    private final String guiTitle;
    private final String prestigeItemMaterial;
    private final String prestigeItemName;
    private final String notEnoughLevel;
    private final String maxPrestige;
    private final String notEnoughGrinded;
    private final String notAvailableDuringMajorEvents;
    private final String presTitle;
    private final String presSubtitle;
    private final String presMessage;
    private final String shopMaterial;
    private final String soonMessage;
    
    public PrestigeGui() {
        this.cachedPrestigeLore = Main.getPrestige().getStringList("Prestige.Lore");
        this.guiTitle = Main.getPrestige().getString("Title");
        this.prestigeItemMaterial = Main.getPrestige().getString("Prestige.Item");
        this.prestigeItemName = Main.getPrestige().getString("Prestige.Name");
        this.notEnoughLevel = Main.getMessages().getString("Prestige.Not.Enough.Level");
        this.maxPrestige = Main.getMessages().getString("Prestige.Max-Prestige");
        this.notEnoughGrinded = Main.getMessages().getString("Prestige.Not.Enough.Grinded");
        this.notAvailableDuringMajorEvents = Main.getMessages().getString("Prestige.Not.Available-During-Major-Events");
        this.presTitle = Main.getPrestige().getString("Prestige.Title");
        this.presSubtitle = Main.getPrestige().getString("Prestige.Subtitle");
        this.presMessage = Main.getPrestige().getString("Prestige.Message");
        this.shopMaterial = Main.getPrestige().getString("Renown-Shop.Item");
        this.soonMessage = Main.getMessages().getString("Soon");
    }
    
    public void openGui(final Player player) {
        final GUI gui = new GUI(player, 27, this.guiTitle);
        final ArrayList<String> lore = new ArrayList<String>();
        for (String s : this.cachedPrestigeLore) {
            lore.add(s.replace("%xpTag%", Main.getXpTag().get(120, PlayerPrestige.get(player).getPrestige())).replace("%resettedLevel%", String.valueOf(this.getStartingLevel())).replace("%grinded%", String.valueOf(PlayerPrestige.get(player).getGrindedGold())).replace("%required%", String.valueOf(PlayerPrestige.getRequiredGrindedGold(PlayerPrestige.get(player).getPrestige() + 1))).replace("%renown%", String.valueOf(PlayerPrestige.getRenownReward(PlayerPrestige.get(player).getPrestige() + 1))).replace("%nextPrestige%", PlayerPrestige.format(PlayerPrestige.get(player).getPrestige() + 1)));
        }
        gui.addItem(new GuiItem(Materials.valueOf(this.prestigeItemMaterial).getItem().setDisplayName(this.prestigeItemName).setLore(lore).build(), 13).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                if (player.getLevel() != 120) {
                    player.sendMessage(PrestigeGui.this.notEnoughLevel);
                    return;
                }
                if (PlayerPrestige.get(player).getPrestige() >= 35) {
                    player.sendMessage(PrestigeGui.this.maxPrestige);
                    return;
                }
                if (PlayerPrestige.get(player).getGrindedGold() < PlayerPrestige.getRequiredGrindedGold(PlayerPrestige.get(player).getPrestige() + 1)) {
                    player.sendMessage(PrestigeGui.this.notEnoughGrinded.replace("%grindedGold%", String.valueOf(PlayerPrestige.get(player).getGrindedGold())).replace("%totalToGrindedGold%", String.valueOf(PlayerPrestige.getRequiredGrindedGold(PlayerPrestige.get(player).getPrestige() + 1))));
                    return;
                }
                if (Main.getPitEventManager().isStarted() && Main.getPitEventManager().getEventType().isMajor()) {
                    player.sendMessage(PrestigeGui.this.notAvailableDuringMajorEvents);
                    return;
                }
                player.setLevel(PrestigeGui.this.getStartingLevel());
                API.getDatabase().setInt(player.getUniqueId(), PrestigeGui.this.getStartingLevel(), "Level", PlayerEconomy.getTableName());
                PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.SET, 0.0);
                int n = 0;
                final PlayerPerk value = PlayerPerk.get(player);
                for (final Perk.PerkType perkType : Perk.PerkType.values()) {
                    if (perkType.isPrestige() && value.isBought(perkType)) {
                        n += Main.getPerkDescription().getPriceRenown(perkType);
                    }
                }
                final Database database = API.getDatabase();
                for (final Perk.PerkType perkType2 : Perk.PerkType.values()) {
                    database.setString(player.getUniqueId(), "false", perkType2 + "_OWNED", Perk.getTableName());
                    database.setInt(player.getUniqueId(), 0, perkType2 + "_SLOT", Perk.getTableName());
                    PlayerPerk.get(player).removePerk(perkType2);
                    PlayerPerk.get(player).deactivatePerk(perkType2);
                }
                final PlayerInventory inventory = player.getInventory();
                inventory.clear();
                inventory.setHelmet(null);
                inventory.setChestplate(null);
                inventory.setLeggings(null);
                inventory.setBoots(null);
                Main.getArena().giveKit(player, true, 10);
                PlayerPrestige.get(player).resetGrindedGold();
                RenownEconomy.get(player).setBalance(RenownEconomy.RenownAction.ADD, 30 + n);
                PlayerPrestige.get(player).levelUpPrestige();
                final int prestige = PlayerPrestige.get(player).getPrestige();
                Bukkit.getPluginManager().callEvent(new PlayerPrestigeEvent(player, prestige - 1, prestige));
                gui.close();
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, PrestigeGui.this.presTitle, 15, 30, 15);
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, PrestigeGui.this.presSubtitle.replace("%prestige%", PlayerPrestige.format(PlayerPrestige.get(player).getPrestige())), 15, 30, 15);
                final String replace = PrestigeGui.this.presMessage.replace("%player%", player.getName()).replace("%prestige%", PlayerPrestige.format(PlayerPrestige.get(player).getPrestige()));
                for (final UUID uuid : Main.getArena().getPlayers()) {
                    if (PlayerChatOption.get(Bukkit.getPlayer(uuid)).isEnabled(ChatOption.ChatOptionType.PRESTIGE)) {
                        Bukkit.getPlayer(uuid).sendMessage(replace);
                    }
                }
            }
        }));
        gui.open();
    }
    
    private int getStartingLevel() {
        return 1;
    }
}
