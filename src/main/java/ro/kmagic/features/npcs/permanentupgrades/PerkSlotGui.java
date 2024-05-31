package ro.kmagic.features.npcs.permanentupgrades;

import ro.kmagic.libapi.versionsupport.sound.Sounds;
import ro.kmagic.features.perk.PlayerPerk;
import ro.kmagic.libapi.gui.ClickAction;
import ro.kmagic.libapi.gui.GuiItem;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.libapi.gui.GUI;
import ro.kmagic.features.prestige.PlayerPrestige;
import org.bukkit.entity.Player;
import ro.kmagic.Main;

import java.util.UUID;
import java.util.HashMap;

public class PerkSlotGui {
    public HashMap<UUID, Integer> playerSlot;
    private final String guiTitle;
    private final String notEnoughLevel;
    private final String notEnoughPrestige;
    
    public PerkSlotGui() {
        this.playerSlot = new HashMap<UUID, Integer>();
        this.guiTitle = Main.getPermanentUpgrades().getString("GUI.Perk.Name");
        this.notEnoughLevel = Main.getPermanentUpgrades().getString("Misc.NotEnoughLevel");
        this.notEnoughPrestige = Main.getPermanentUpgrades().getString("Misc.Not-Enough-Prestige");
    }
    
    public void open(final Player player, final int i) {
        this.playerSlot.put(player.getUniqueId(), i);
        final boolean b = PlayerPrestige.get(player).getPrestige() != 0;
        final GUI gui = new GUI(player, b ? 54 : 36, this.guiTitle);
        this.addPerk(player, Perk.PerkType.GOLDEN_HEADS, gui, 10);
        this.addPerk(player, Perk.PerkType.FISHING_ROD, gui, 11);
        this.addPerk(player, Perk.PerkType.LAVA_BUCKET, gui, 12);
        this.addPerk(player, Perk.PerkType.STRENGTH_CHAINING, gui, 13);
        this.addPerk(player, Perk.PerkType.ENDLESS_QUIVER, gui, 14);
        this.addPerk(player, Perk.PerkType.MINEMAN, gui, 15);
        this.addPerk(player, Perk.PerkType.SAFETY_FIRST, gui, 16);
        this.addPerk(player, Perk.PerkType.TRICKLE_DOWN, gui, 19);
        this.addPerk(player, Perk.PerkType.LUCKY_DIAMOND, gui, 20);
        this.addPerk(player, Perk.PerkType.SPAMMER, gui, 21);
        this.addPerk(player, Perk.PerkType.BOUNTY_HUNTER, gui, 22);
        this.addPerk(player, Perk.PerkType.STREAKER, gui, 23);
        this.addPerk(player, Perk.PerkType.GLADIATOR, gui, 24);
        this.addPerk(player, Perk.PerkType.VAMPIRE, gui, 25);
        if (b) {
            this.addPerkPrestige(player, Perk.PerkType.OVERHEAL, gui, 28);
            this.addPerkPrestige(player, Perk.PerkType.BARBARIAN, gui, 29);
            this.addPerkPrestige(player, Perk.PerkType.DIRTY, gui, 30);
            this.addPerkPrestige(player, Perk.PerkType.RAMBO, gui, 31);
            this.addPerkPrestige(player, Perk.PerkType.OLYMPUS, gui, 32);
            this.addPerkPrestige(player, Perk.PerkType.FIRST_STRIKE, gui, 33);
            this.addPerkPrestige(player, Perk.PerkType.ASSIST_STREAKER, gui, 34);
            this.addPerkPrestige(player, Perk.PerkType.MARATHON, gui, 37);
            this.addPerkPrestige(player, Perk.PerkType.SOUP, gui, 38);
            this.addPerkPrestige(player, Perk.PerkType.RECON, gui, 39);
            this.addPerkPrestige(player, Perk.PerkType.CONGLOMERATE, gui, 40);
            this.addPerkPrestige(player, Perk.PerkType.KUNG_FU_KNOWLEDGE, gui, 41);
            this.addPerkPrestige(player, Perk.PerkType.CO_OP_CAT, gui, 42);
            this.addPerkPrestige(player, Perk.PerkType.THICK, gui, 43);
        }
        gui.addItem(new GuiItem(Main.getPerk().getButton(Perk.ButtonType.BACK), b ? 49 : 31).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                Main.getPerkGui().open(player);
            }
        }));
        this.addDeactivateButton(player, i, gui);
        gui.open();
    }
    
    public void addPerk(final Player player, final Perk.PerkType perkType, final GUI gui, final int n) {
        if (PlayerPrestige.get(player).getPrestige() <= 0 && !this.hasLevel(player, perkType)) {
            gui.addItem(new GuiItem(Main.getPerk().getDescription(perkType, Perk.DescriptionType.LOCKED, 0, null), n));
        }
        else {
            final PlayerPerk value = PlayerPerk.get(player);
            if (!value.isBought(perkType)) {
                gui.addItem(new GuiItem(Main.getPerk().getDescription(perkType, Perk.DescriptionType.NOT_OWNED, 0, null), n));
            }
            else {
                gui.addItem(new GuiItem(Main.getPerk().getDescription(perkType, value.isActivated(perkType) ? Perk.DescriptionType.ACTIVATED : Perk.DescriptionType.DEACTIVATED, 0, null), n));
            }
        }
        gui.getItem(n).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                PerkSlotGui.this.executePerk(player, perkType);
            }
        });
    }
    
    public void addPerkPrestige(final Player player, final Perk.PerkType perkType, final GUI gui, final int n) {
        if (!this.hasPrestige(player, perkType)) {
            gui.addItem(new GuiItem(Main.getPerk().getDescription(perkType, Perk.DescriptionType.LOCKED, 0, null), n));
        }
        else if (PlayerPrestige.get(player).getPrestige() <= Main.getPerkDescription().getRequiredPrestige(perkType) && !this.hasLevel(player, perkType)) {
            gui.addItem(new GuiItem(Main.getPerk().getDescription(perkType, Perk.DescriptionType.LOCKED, 0, null), n));
        }
        else {
            final PlayerPerk value = PlayerPerk.get(player);
            if (!value.isBought(perkType)) {
                gui.addItem(new GuiItem(Main.getPerk().getDescriptionPrestige(player, perkType, Perk.DescriptionType.NOT_OWNED, null), n));
            }
            else {
                gui.addItem(new GuiItem(Main.getPerk().getDescription(perkType, value.isActivated(perkType) ? Perk.DescriptionType.ACTIVATED : Perk.DescriptionType.DEACTIVATED, 0, null), n));
            }
        }
        gui.getItem(n).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                PerkSlotGui.this.executePrestigePerk(player, perkType);
            }
        });
    }
    
    private void executePerk(final Player player, final Perk.PerkType perkType) {
        if (this.playerSlot.containsKey(player.getUniqueId())) {
            this.checkPerk(player, perkType, this.playerSlot.get(player.getUniqueId()));
        }
    }
    
    private void executePrestigePerk(final Player player, final Perk.PerkType perkType) {
        if (this.playerSlot.containsKey(player.getUniqueId())) {
            this.checkPerk(player, perkType, this.playerSlot.get(player.getUniqueId()));
        }
    }
    
    private void checkPerk(final Player player, final Perk.PerkType perkType, final int n) {
        if (!Main.getArena().displayFeature(player, Main.getPerkDescription().getRequiredLevel(perkType))) {
            player.sendMessage(this.notEnoughLevel);
            Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
        }
        else if (!PlayerPerk.get(player).isBought(perkType)) {
            if (perkType.isPrestige()) {
                if (this.hasPrestige(player, perkType)) {
                    if (this.hasLevel(player, perkType)) {
                        Main.getPerk().buyPrestige(player, perkType);
                    }
                    else if (PlayerPrestige.get(player).getPrestige() > Main.getPerkDescription().getRequiredPrestige(perkType)) {
                        Main.getPerk().buyPrestige(player, perkType);
                    }
                    else {
                        player.sendMessage(this.notEnoughLevel);
                        Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
                    }
                }
                else if (!this.hasPrestige(player, perkType)) {
                    player.sendMessage(this.notEnoughPrestige);
                    Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
                }
                else if (!this.hasLevel(player, perkType)) {
                    player.sendMessage(this.notEnoughLevel);
                    Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
                }
            }
            else if (Main.getArena().displayFeature(player, Main.getPerkDescription().getRequiredLevel(perkType))) {
                Main.getPerk().buy(player, perkType);
            }
            else {
                player.sendMessage(this.notEnoughLevel);
                Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
            }
        }
        else {
            Main.getPerk().activate(player, perkType, n);
            this.open(player, n);
        }
    }
    
    private boolean hasLevel(final Player player, final Perk.PerkType perkType) {
        return player.getLevel() >= Main.getPerkDescription().getRequiredLevel(perkType);
    }
    
    private boolean hasPrestige(final Player player, final Perk.PerkType perkType) {
        return PlayerPrestige.get(player).getPrestige() >= Main.getPerkDescription().getRequiredPrestige(perkType);
    }
    
    private void addDeactivateButton(final Player player, final int n, final GUI gui) {
        final Perk.PerkType[] values = Perk.PerkType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (PlayerPerk.get(player).isActivated(values[i], n)) {
                gui.addItem(new GuiItem(Main.getPerk().getButton(Perk.ButtonType.DEACTIVATE), (PlayerPrestige.get(player).getPrestige() == 0) ? 32 : 50).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        Main.getPerk().deactivate(player, PerkSlotGui.this.playerSlot.get(player.getUniqueId()));
                        Main.getPerkGui().open(player);
                    }
                }));
                break;
            }
        }
    }
}
