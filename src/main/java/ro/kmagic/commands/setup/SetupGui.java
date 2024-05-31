package ro.kmagic.commands.setup;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.command.SubCommand;
import ro.kmagic.libapi.gui.ClickAction;
import ro.kmagic.libapi.gui.GUI;
import ro.kmagic.libapi.gui.GuiItem;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.Main;
import ro.kmagic.commands.setup.utils.SetupUtils;

import java.util.Arrays;

public class SetupGui extends SubCommand {
    public SetupGui() {
        super("gui", SetupUtils.getSetupPermissions());
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return;
        }
        final Player player = (Player)commandSender;
        if (!Main.getWorldManager().isOnMap(player)) {
            final GUI gui = new GUI(player, 9, "&aTHEPIT &7- &fTeleport to ThePit's world");
            gui.addItem(new GuiItem(Materials.COMMAND_BLOCK.getItem().flag().add(ItemFlag.values()).setDisplayName("&aTeleport to ThePit world").setLore(Arrays.asList("", "&7Click to teleport in", "&7ThePit's world in order", "&7to setup the arena.")).build(), 2).addClickAction(new ClickAction() {
                @Override
                public void onClick(final GuiItem guiItem, final GUI gui) {
                    player.performCommand("thepit setMap");
                    player.closeInventory();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have been teleported to ThePit's map. &cOpening Setup GUI..."));
                    new BukkitRunnable() {
                        public void run() {
                            player.performCommand("thepit gui");
                            player.closeInventory();
                            player.sendMessage("&7Setup GUI was opened, continuing the setup...");
                        }
                    }.runTaskLater(API.getPlugin(), 30L);
                }
            }));
            for (int i = 0; i <= 26; ++i) {
                if (gui.getItem(i) == null) {
                    gui.addItem(new GuiItem(this.glass(), i));
                }
            }
            gui.open(player);
            return;
        }
        final GUI gui2 = new GUI(player, InventoryType.HOPPER, "&aTHEPIT &7 - &fSetup");
        gui2.addItem(new GuiItem(Materials.RED_BANNER.getItem().flag().add(ItemFlag.values()).setDisplayName("&cRequired").setLore(Arrays.asList("&7In order to run this", "&7server with this minigame,", "&7you must set all required steps.")).build(), 0).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                SetupGui.this.requiredSteps(player);
            }
        }));
        gui2.addItem(new GuiItem(Materials.LIME_BANNER.getItem().flag().add(ItemFlag.values()).setDisplayName("&aOptional").setLore(Arrays.asList("&7In order to enhance the", "&7gamemode experience, you", "&7must set all optional steps.")).build(), 2).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                SetupGui.this.optionalSteps(player);
            }
        }));
        for (int j = 0; j <= 4; ++j) {
            if (gui2.getItem(j) == null) {
                gui2.addItem(new GuiItem(this.glass(), j));
            }
        }
        gui2.open(player);
    }
    
    private ItemStack glass() {
        return new ItemStack(Materials.BLACK_STAINED_GLASS_PANE.getItem().flag().add(ItemFlag.values()).setDisplayName(" ").build());
    }
    
    private void requiredSteps(final Player player) {
        final GUI gui = new GUI(player, 27, "&aTHEPIT &7- &fRequired steps");
        gui.addItem(new GuiItem(Materials.ENDER_PEARL.getItem().flag().add(ItemFlag.values()).setDisplayName("&aSet pit Spawn").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.SPAWN, "", false), "", "&7Go to arena's spawn location", "&7and click this item to set", "&7the spawn point.")).build(), 10).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thepit setSpawn");
                player.closeInventory();
            }
        }));
        int n = 12;
        int n2 = 13;

        gui.addItem(new GuiItem(Materials.DARK_OAK_DOOR.getItem().flag().add(ItemFlag.values()).setDisplayName("&aSet server Lobby").setLore(Arrays.asList("&fStatus:" + Main.getSetupAssister().getStatus(SetupUtils.SetupType.LOBBY, "", false), "", "&7Go to your lobby and", "&7type: &f/thePit setLobby", "&7to set arena's lobby.")).build(), 11).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thepit setLobby");
                player.closeInventory();
            }
        }));

        gui.addItem(new GuiItem(Materials.COMPASS.getItem().flag().add(ItemFlag.values()).setDisplayName("&aSet pit Spawn Region").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.SPAWN_REGION, "", false), "", "&7Go to arena's spawn location", "&7and set pos1 and pos2", "&7of the spawn area.", "&7Example: Like WorldGuard region works.", "", "&c&o&lNOTE!", "&cWhen making the region, pos1", "&cand pos2 must contain the spawn!")).build(), n).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                final GUI gui2 = new GUI(player, 27, "&aTHEPIT &7- &fSpawn region");
                gui2.addItem(new GuiItem(Materials.DIAMOND_AXE.getItem().enchantment().addUnsafe(Enchantment.DURABILITY, 1).flag().add(ItemFlag.values()).setDisplayName("&aWand").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.SPAWN_REGION, "", false), "", "&7Click to get the wand", "&7to set spawn's region.")).build(), 11).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        player.performCommand("thepit setSpawnRegion wand");
                        player.closeInventory();
                    }
                }));
                gui2.addItem(new GuiItem(Materials.COMPASS.getItem().flag().add(ItemFlag.values()).setDisplayName("&aPos 1").setLore(Arrays.asList("&fStats: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.SPAWN_REGION, "", false), "", "&7Click to set pos1", "&7of spawn's region.")).build(), 13).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        player.performCommand("thepit setSpawnRegion pos1");
                        player.closeInventory();
                    }
                }));
                gui2.addItem(new GuiItem(Materials.COMPASS.getItem().flag().add(ItemFlag.values()).setAmount(2).setDisplayName("&aPos 2").setLore(Arrays.asList("&fStats: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.SPAWN_REGION, "", false), "", "&7Click to set pos2", "&7of spawn's region.")).build(), 15).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        player.performCommand("thepit setSpawnRegion pos2");
                        player.closeInventory();
                    }
                }));
                gui2.addItem(SetupGui.this.backTo(MenuType.REQUIRED, player));
                for (int i = 0; i <= 26; ++i) {
                    if (gui2.getItem(i) == null) {
                        gui2.addItem(new GuiItem(SetupGui.this.glass(), i));
                    }
                }
                gui2.open(player);
            }
        }));
        gui.addItem(new GuiItem(Materials.IRON_AXE.getItem().enchantment().addUnsafe(Enchantment.DURABILITY, 1).flag().add(ItemFlag.values()).setDisplayName("&aSet pit Hole").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.PIT_HOLE, "", false), "", "&7Go to arena's spawn and", "&7set pos1 and pos2 of the pit hole.", "", "&c&o&lNOTE!", "&cWhen setting the region, pos1", "&cand pos2 must contain the pit hole!")).build(), n2).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thepit setPitHole wand");
                player.closeInventory();
            }
        }));
        gui.addItem(new GuiItem(Materials.VILLAGER_SPAWN_EGG.getItem().flag().add(ItemFlag.values()).setDisplayName("&aSet pit NPCs").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.NPCS, "", false), "", "&7Click to open npc's list.")).build(), 14).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                final GUI gui2 = new GUI(player, 27, "&aTHEPIT &7- &fArena NPCs");
                gui2.addItem(new GuiItem(Materials.BOOK.getItem().flag().add(ItemFlag.values()).setDisplayName("&aStats NPC").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.NPC_STATS, "", false), "", "&7Click to set '&fStats&7' NPC.")).build(), 10).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        player.performCommand("thePit setNpc stats");
                        player.closeInventory();
                    }
                }));
                gui2.addItem(new GuiItem(Materials.ARROW.getItem().flag().add(ItemFlag.values()).setDisplayName("&aNon Permanent Items NPC").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.NPC_NON_PERMANENT_ITEMS, "", false), "", "&7Click to set '&fNon Permanent Items&7' NPC.")).build(), 11).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        player.performCommand("thePit setNpc nonPermanentItems");
                        player.closeInventory();
                    }
                }));
                gui2.addItem(new GuiItem(Materials.NETHER_STAR.getItem().flag().add(ItemFlag.values()).setDisplayName("&aPermanent Upgrades NPC").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.NPC_PERMANENT_UPGRADES, "", false), "", "&7Click to set '&fPermanent Upgrades&7' NPC.")).build(), 12).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        player.performCommand("thePit setNpc permanentUpgrades");
                        player.closeInventory();
                    }
                }));
                gui2.addItem(new GuiItem(Materials.DARK_OAK_DOOR.getItem().flag().add(ItemFlag.values()).setDisplayName("&aThe Keeper NPC").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.NPC_THE_KEEPER, "", false), "", "&7Click to set '&fThe Keeper&7' NPC.")).build(), 14).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        player.performCommand("thePit setNpc theKeeper");
                        player.closeInventory();
                    }
                }));
                gui2.addItem(new GuiItem(Materials.GOLD_NUGGET.getItem().flag().add(ItemFlag.values()).setDisplayName("&aPrestige NPC").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.NPC_PRESTIGE, "", false), "", "&7Click to set '&fPrestige&7' NPC.")).build(), 15).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        player.performCommand("thePit setNpc prestige");
                        player.closeInventory();
                    }
                }));
                gui2.addItem(new GuiItem(Materials.PAPER.getItem().flag().add(ItemFlag.values()).setDisplayName("&aQuest Master NPC").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.NPC_QUEST_MASTER, "", false), "", "&7Click to set '&fQuest Master&7' NPC.")).build(), 16).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        player.performCommand("thePit setNpc questMaster");
                        player.closeInventory();
                    }
                }));
                gui2.addItem(SetupGui.this.backTo(MenuType.REQUIRED, player));
                for (int i = 0; i <= 26; ++i) {
                    if (gui2.getItem(i) == null) {
                        gui2.addItem(new GuiItem(SetupGui.this.glass(), i));
                    }
                }
                gui2.open(player);
            }
        }));
        gui.addItem(new GuiItem(Materials.GOLD_INGOT.getItem().flag().add(ItemFlag.values()).setDisplayName("&aSet pit Random Gold").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.RANDOM_GOLD, "", false), "", "&7You must right-click", "&7with the wand on", "&7the block that should", "&7spawn gold on top.")).build(), 16).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thePit setRandomGold");
                player.closeInventory();
            }
        }));
        gui.addItem(this.backTo(MenuType.MAIN, player));
        for (int i = 0; i <= 26; ++i) {
            if (gui.getItem(i) == null) {
                gui.addItem(new GuiItem(this.glass(), i));
            }
        }
        gui.open(player);
    }
    
    private void optionalSteps(final Player player) {
        final GUI gui = new GUI(player, 27, "&aTHEPIT &7- &fOptional steps");
        gui.addItem(new GuiItem(Materials.ENDER_CHEST.getItem().flag().add(ItemFlag.values()).setDisplayName("&aEnderchest").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.ENDERCHEST, "", false), "", "&7You must right-click", "&7with the wand on", "&7the enderchests from spawn")).build(), 10).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thePit setEnderchest");
                player.closeInventory();
            }
        }));
        gui.addItem(new GuiItem(Materials.ARMOR_STAND.getItem().flag().add(ItemFlag.values()).setDisplayName("&aPit hole hologram").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.PIT_HOLE_HOLOGRAM, "", false), "", "&7Click to set the hologarm", "&7in center of the pit hole.")).build(), 11).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thepit setPitHole hologram");
                player.closeInventory();
            }
        }));
        gui.addItem(new GuiItem(Materials.ENCHANTED_BOOK.getItem().flag().add(ItemFlag.values()).setDisplayName("&aUnlocked Features hologram").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.UNLOCKED_FEATURES_HOLOGRAM, "", false), "", "&7Click to set the &eUnlocked Features", "&7hologram")).build(), 12).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thepit setunlockedFeaturesHologram");
                player.closeInventory();
            }
        }));
        gui.addItem(new GuiItem(Materials.CAULDRON.getItem().flag().add(ItemFlag.values()).setDisplayName("&aEvent locations & holograms").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.EVENTS_LOCATIONS_HOLOGRAMS, "", false), "", "&7Click to open the event", "&7locations and holograms list.")).build(), 13).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                SetupGui.this.setEventLocsHolos(player);
            }
        }));
        gui.addItem(new GuiItem(Materials.DIAMOND_BLOCK.getItem().flag().add(ItemFlag.values()).setDisplayName("&aKing of the Hill").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.KOTH, "", false), "", "&7Choose random location", "&7from an event area to", "&7set 'King of the Hill'.")).build(), 14).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thepit setEvent koth");
                player.closeInventory();
            }
        }));
        gui.addItem(new GuiItem(Materials.CHEST.getItem().flag().add(ItemFlag.values()).setDisplayName("&aCare Package").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.CARE_PACKAGE, "", false), "", "&7Choose random location", "&7from an event area to", "&7set 'Care Package'.")).build(), 15).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thepit setEvent carePackage");
                player.closeInventory();
            }
        }));
        gui.addItem(new GuiItem(Materials.BLACK_STAINED_GLASS.getItem().flag().add(ItemFlag.values()).setDisplayName("&aRage pit").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.RAGE_PIT, "", false), "", "&7Cover your spawn area", "&7with &c&o'BEDROCK'&7, then", "&7you must make a region", "&7that contains the BEDROCK blocks.", "&fExample: &7WorldGuard plugin")).build(), 16).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                final GUI gui2 = new GUI(player, 27, "&aTHEPIT &7- &fSetting rage pit");
                gui2.addItem(new GuiItem(Materials.COMPASS.getItem().flag().add(ItemFlag.values()).setDisplayName("&aPos 1").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.RAGE_PIT, "", false), "", "&7Click to set rage", "&7pit's area pos1.")).build(), 12).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        player.performCommand("thepit setEvent ragePit pos1");
                        player.closeInventory();
                    }
                }));
                gui2.addItem(new GuiItem(Materials.COMPASS.getItem().flag().add(ItemFlag.values()).setDisplayName("&aPos 2").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.RAGE_PIT, "", false), "", "&7Click to set rage", "&7pit's area pos2.")).build(), 14).addClickAction(new ClickAction() {
                    @Override
                    public void onClick(final GuiItem guiItem, final GUI gui) {
                        player.performCommand("thepit setEvent ragePit pos2");
                        player.closeInventory();
                    }
                }));
                gui2.addItem(SetupGui.this.backTo(MenuType.OPTIONAL, player));
                for (int i = 0; i <= 26; ++i) {
                    if (gui2.getItem(i) == null) {
                        gui2.addItem(new GuiItem(SetupGui.this.glass(), i));
                    }
                }
                gui2.open(player);
            }
        }));
        gui.addItem(this.backTo(MenuType.MAIN, player));
        for (int i = 0; i <= 26; ++i) {
            if (gui.getItem(i) == null) {
                gui.addItem(new GuiItem(this.glass(), i));
            }
        }
        gui.open(player);
    }
    
    private void setEventLocsHolos(final Player player) {
        final GUI gui = new GUI(player, 27, "&aTHEPIT &7- &fEvent loc & holo");
        gui.addItem(new GuiItem(Materials.COMPASS.getItem().flag().add(ItemFlag.values()).setDisplayName("&aEvent locations").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.EVENTS_LOCATIONS, "", false), "", "&7Click to set event regions.")).build(), 12).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                SetupGui.this.setEventLocs(player);
            }
        }));
        gui.addItem(new GuiItem(Materials.ARMOR_STAND.getItem().flag().add(ItemFlag.values()).setDisplayName("&aEvent holograms").setLore(Arrays.asList("&fStatus: " + Main.getSetupAssister().getStatus(SetupUtils.SetupType.EVENTS_HOLOGRAMS, "", false), "", "&7Click to set arena's", "&7events holograms.")).build(), 14).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thepit setEvent holograms");
                player.closeInventory();
            }
        }));
        gui.addItem(this.backTo(MenuType.OPTIONAL, player));
        for (int i = 0; i <= 26; ++i) {
            if (gui.getItem(i) == null) {
                gui.addItem(new GuiItem(this.glass(), i));
            }
        }
        gui.open(player);
    }
    
    private void setEventLocs(final Player player) {
        final GUI gui = new GUI(player, 27, "&aEvent locations");
        gui.addItem(new GuiItem(Materials.DIRT.getItem().flag().add(ItemFlag.values()).setDisplayName("&aMountain").setLore(Arrays.asList("&fStatus: " + String.valueOf(Main.getEventsRegion().isSet("Mountain")).replace("false", "&c\u2716").replace("true", "&a\u2714"), "", "&7Click to set", "&7'Mountain' arena region.")).build(), 10).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                SetupGui.this.setEventAreaPos(player, "Mountain");
            }
        }));
        gui.addItem(new GuiItem(Materials.LAVA_BUCKET.getItem().flag().add(ItemFlag.values()).setDisplayName("&aLava").setLore(Arrays.asList("&fStatus: " + String.valueOf(Main.getEventsRegion().isSet("Lava")).replace("false", "&c\u2716").replace("true", "&a\u2714"), "", "&7Click to set", "&7'Lava' arena region.")).build(), 12).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                SetupGui.this.setEventAreaPos(player, "Lava");
            }
        }));
        gui.addItem(new GuiItem(Materials.GRASS.getItem().flag().add(ItemFlag.values()).setDisplayName("&aSky").setLore(Arrays.asList("&fStatus: " + String.valueOf(Main.getEventsRegion().isSet("Sky")).replace("false", "&c\u2716").replace("true", "&a\u2714"), "", "&7Click to set", "&7'Sky' arena region.")).build(), 14).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                SetupGui.this.setEventAreaPos(player, "Sky");
            }
        }));
        gui.addItem(new GuiItem(Materials.WATER_BUCKET.getItem().flag().add(ItemFlag.values()).setDisplayName("&aWater").setLore(Arrays.asList("&fStatus: " + String.valueOf(Main.getEventsRegion().isSet("Water")).replace("false", "&c\u2716").replace("true", "&a\u2714"), "", "&7Click to set", "&7'Water' arena region.")).build(), 16).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                SetupGui.this.setEventAreaPos(player, "Water");
            }
        }));
        gui.addItem(this.backTo(MenuType.LOCATIONS_HOLOGRAMS, player));
        for (int i = 0; i <= 26; ++i) {
            if (gui.getItem(i) == null) {
                gui.addItem(new GuiItem(this.glass(), i));
            }
        }
        gui.open(player);
    }
    
    private void setEventAreaPos(final Player player, final String str) {
        final GUI gui = new GUI(player, 27, "&aTHEPIT &7- &fSetting " + str);
        gui.addItem(new GuiItem(Materials.COMPASS.getItem().flag().add(ItemFlag.values()).setDisplayName("&aPos 1").setLore(Arrays.asList("&fStatus: " + String.valueOf(Main.getEventsRegion().isSet(str)).replace("false", "&c\u2716").replace("true", "&a\u2714"), "", "&7Click to set '" + str + "'", "&7area pos1 at your location.")).build(), 12).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thepit setEvent locations pos1 " + str);
                player.closeInventory();
            }
        }));
        gui.addItem(new GuiItem(Materials.COMPASS.getItem().flag().add(ItemFlag.values()).setAmount(2).setDisplayName("&aPos 2").setLore(Arrays.asList("&fStatus: " + String.valueOf(Main.getEventsRegion().isSet(str)).replace("false", "&c\u2716").replace("true", "&a\u2714"), "", "&7Click to set '" + str + "'", "&7area pos2 at your location.")).build(), 14).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                player.performCommand("thepit setEvent locations pos2 " + str);
                player.closeInventory();
            }
        }));
        gui.addItem(this.backTo(MenuType.LOCATIONS, player));
        for (int i = 0; i <= 26; ++i) {
            if (gui.getItem(i) == null) {
                gui.addItem(new GuiItem(this.glass(), i));
            }
        }
        gui.open(player);
    }
    
    private GuiItem backTo(final MenuType menuType, final Player player) {
        return new GuiItem(Materials.BARRIER.getItem().flag().add(ItemFlag.values()).setDisplayName("&cBack to " + menuType.getName() + " menu").setLore(Arrays.asList("", "&7Click to go to " + menuType.getName() + " setup menu")).build(), 26).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                switch (menuType) {
                    case REQUIRED: {
                        SetupGui.this.requiredSteps(player);
                        break;
                    }
                    case OPTIONAL: {
                        SetupGui.this.optionalSteps(player);
                        break;
                    }
                    case MAIN: {
                        player.performCommand("thePit gui");
                        break;
                    }
                    case LOCATIONS: {
                        SetupGui.this.setEventLocs(player);
                        break;
                    }
                    case LOCATIONS_HOLOGRAMS: {
                        SetupGui.this.setEventLocsHolos(player);
                        break;
                    }
                }
            }
        });
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
    
    public enum MenuType {
        REQUIRED("Required"), 
        OPTIONAL("Optional"), 
        MAIN("Main"), 
        LOCATIONS("Locations"), 
        LOCATIONS_HOLOGRAMS("Locations & Holograms");
        
        private final String name;
        
        MenuType(final String name2) {
            this.name = name2;
        }
        
        public String getName() {
            return this.name;
        }
    }
}
