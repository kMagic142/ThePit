package ro.kmagic.features.npcs.permanentupgrades;

import ro.kmagic.libapi.gui.ClickAction;
import ro.kmagic.features.perk.PlayerPerk;
import ro.kmagic.libapi.gui.GuiItem;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.prestige.PlayerPrestige;
import ro.kmagic.libapi.gui.GUI;
import org.bukkit.entity.Player;
import ro.kmagic.Main;

public class PerkGui {
    private final String guiTitle;
    private final String notUnlocked;
    
    public PerkGui() {
        this.guiTitle = Main.getPermanentUpgrades().getString("GUI.Name");
        this.notUnlocked = Main.getPermanentUpgrades().getString("Misc.NotUnlocked");
    }
    
    public void open(final Player player) {
        final GUI gui = new GUI(player, 27, this.guiTitle);
        for (int i = 1; i <= 3; ++i) {
            this.displayPerk(player, i, gui);
        }
        if (PlayerPrestige.get(player).getPrestige() >= 4) {
            this.displayPerk(player, 4, gui);
        }
        gui.open();
    }
    
    private void displayPerk(final Player player, final int i, final GUI gui) {
        if (!Main.getArena().displayFeature(player, Main.getPermanentUpgrades().getInt("Player.PerkSlot." + i + ".Level.Required"))) {
            gui.addItem(new GuiItem(Main.getPerk().getDescription(null, Perk.DescriptionType.PERK_SLOT_NOT_OWNED, i, Materials.BEDROCK), 11 + i));
        }
        else {
            boolean b = true;
            for (final Perk.PerkType perkType : Perk.PerkType.values()) {
                if (PlayerPerk.get(player).isActivated(perkType, i)) {
                    b = false;
                    gui.addItem(new GuiItem(Main.getPerk().getDescription(perkType, Perk.DescriptionType.SELECTED, i, null), 11 + i));
                    break;
                }
            }
            if (b) {
                gui.addItem(new GuiItem(Main.getPerk().getDescription(null, Perk.DescriptionType.PERK_SLOT, i, Materials.DIAMOND_BLOCK), 11 + i));
            }
        }
        gui.getItem(11 + i).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                if (Main.getArena().displayFeature(player, Main.getPermanentUpgrades().getInt("Player.PerkSlot." + i + ".Level.Required"))) {
                    Main.getPerkSlotGui().open(player, i);
                }
                else {
                    player.sendMessage(PerkGui.this.notUnlocked);
                }
            }
        });
    }
}
