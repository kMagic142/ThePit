package ro.kmagic.files.perk;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import java.util.List;
import ro.kmagic.files.perk.perks.prestige.Thick;
import ro.kmagic.files.perk.perks.prestige.CoOpCat;
import ro.kmagic.files.perk.perks.prestige.KungFuKnowledge;
import ro.kmagic.files.perk.perks.prestige.Conglomerate;
import ro.kmagic.files.perk.perks.prestige.Recon;
import ro.kmagic.files.perk.perks.prestige.Soup;
import ro.kmagic.files.perk.perks.prestige.Marathon;
import ro.kmagic.files.perk.perks.prestige.AssistStreaker;
import ro.kmagic.files.perk.perks.prestige.FirstStrike;
import ro.kmagic.files.perk.perks.prestige.Olympus;
import ro.kmagic.files.perk.perks.prestige.Rambo;
import ro.kmagic.files.perk.perks.prestige.Dirty;
import ro.kmagic.files.perk.perks.prestige.Barbarian;
import ro.kmagic.files.perk.perks.prestige.Overheal;
import ro.kmagic.files.perk.perks.Vampire;
import ro.kmagic.files.perk.perks.TrickleDown;
import ro.kmagic.files.perk.perks.StrengthChaining;
import ro.kmagic.files.perk.perks.Streaker;
import ro.kmagic.files.perk.perks.Spammer;
import ro.kmagic.files.perk.perks.SafetyFirst;
import ro.kmagic.files.perk.perks.Mineman;
import ro.kmagic.files.perk.perks.LuckyDiamond;
import ro.kmagic.files.perk.perks.LavaBucket;
import ro.kmagic.files.perk.perks.GoldenHeads;
import ro.kmagic.files.perk.perks.Gladiator;
import ro.kmagic.files.perk.perks.FishingRod;
import ro.kmagic.files.perk.perks.EndlessQuiver;
import ro.kmagic.files.perk.perks.BountyHunter;
import ro.kmagic.libapi.utils.FileManager;
import ro.kmagic.features.perk.Perk;

import java.util.HashMap;

public class PerkDescription {
    private static final String perk = "Perk.";
    private static final String gui = "GUI.";
    private static final String activated = "Activated.";
    private static final String deactivated = "Deactivated.";
    private static final String notOwned = "NotOwned.";
    private static final String locked = "Locked.";
    private static final String confirm = "Confirm.";
    private static final String deny = "Deny.";
    private static final String selected = "Selected.";
    private static final String name = "Name";
    private static final String lore = "Lore";
    public static final String perkRequiredLevel = "Perk.RequiredLevel";
    public static final String perkRequiredPrestige = "Perk.Required-Prestige";
    public static final String perkPrice = "Perk.Price";
    public static final String perkPriceRenown = "Perk.Price-Renown";
    public static final String perkBought = "Perk.Bought";
    public static final String perkItemName = "Perk.ItemName";
    public static final String perkIcon = "Perk.Icon";
    public static final String guiActivatedName = "GUI.Activated.Name";
    public static final String guiActivatedLore = "GUI.Activated.Lore";
    public static final String guiDeactivatedName = "GUI.Deactivated.Name";
    public static final String guiDeactivatedLore = "GUI.Deactivated.Lore";
    public static final String guiNotOwnedName = "GUI.NotOwned.Name";
    public static final String guiNotOwnedLore = "GUI.NotOwned.Lore";
    public static final String guiLockedName = "GUI.Locked.Name";
    public static final String guiLockedLore = "GUI.Locked.Lore";
    public static final String guiConfirmName = "GUI.Confirm.Name";
    public static final String guiConfirmLore = "GUI.Confirm.Lore";
    public static final String guiDenyName = "GUI.Deny.Name";
    public static final String guiDenyLore = "GUI.Deny.Lore";
    public static final String guiSelectedLore = "GUI.Selected.Lore";
    private final HashMap<Perk.PerkType, FileManager> perkFiles;
    
    public PerkDescription() {
        (this.perkFiles = new HashMap<Perk.PerkType, FileManager>()).put(Perk.PerkType.BOUNTY_HUNTER, new BountyHunter());
        this.perkFiles.put(Perk.PerkType.ENDLESS_QUIVER, new EndlessQuiver());
        this.perkFiles.put(Perk.PerkType.FISHING_ROD, new FishingRod());
        this.perkFiles.put(Perk.PerkType.GLADIATOR, new Gladiator());
        this.perkFiles.put(Perk.PerkType.GOLDEN_HEADS, new GoldenHeads());
        this.perkFiles.put(Perk.PerkType.LAVA_BUCKET, new LavaBucket());
        this.perkFiles.put(Perk.PerkType.LUCKY_DIAMOND, new LuckyDiamond());
        this.perkFiles.put(Perk.PerkType.MINEMAN, new Mineman());
        this.perkFiles.put(Perk.PerkType.SAFETY_FIRST, new SafetyFirst());
        this.perkFiles.put(Perk.PerkType.SPAMMER, new Spammer());
        this.perkFiles.put(Perk.PerkType.STREAKER, new Streaker());
        this.perkFiles.put(Perk.PerkType.STRENGTH_CHAINING, new StrengthChaining());
        this.perkFiles.put(Perk.PerkType.TRICKLE_DOWN, new TrickleDown());
        this.perkFiles.put(Perk.PerkType.VAMPIRE, new Vampire());
        this.perkFiles.put(Perk.PerkType.OVERHEAL, new Overheal());
        this.perkFiles.put(Perk.PerkType.BARBARIAN, new Barbarian());
        this.perkFiles.put(Perk.PerkType.DIRTY, new Dirty());
        this.perkFiles.put(Perk.PerkType.RAMBO, new Rambo());
        this.perkFiles.put(Perk.PerkType.OLYMPUS, new Olympus());
        this.perkFiles.put(Perk.PerkType.FIRST_STRIKE, new FirstStrike());
        this.perkFiles.put(Perk.PerkType.ASSIST_STREAKER, new AssistStreaker());
        this.perkFiles.put(Perk.PerkType.MARATHON, new Marathon());
        this.perkFiles.put(Perk.PerkType.SOUP, new Soup());
        this.perkFiles.put(Perk.PerkType.RECON, new Recon());
        this.perkFiles.put(Perk.PerkType.CONGLOMERATE, new Conglomerate());
        this.perkFiles.put(Perk.PerkType.KUNG_FU_KNOWLEDGE, new KungFuKnowledge());
        this.perkFiles.put(Perk.PerkType.CO_OP_CAT, new CoOpCat());
        this.perkFiles.put(Perk.PerkType.THICK, new Thick());
    }
    
    public String getBoughtMessage(final Perk.PerkType key) {
        return this.perkFiles.get(key).getString("Perk.Bought");
    }
    
    public String getConfirmName(final Perk.PerkType key) {
        return this.perkFiles.get(key).getString("GUI.Confirm.Name");
    }
    
    public List<String> getConfirmLore(final Perk.PerkType key) {
        return this.perkFiles.get(key).getStringList("GUI.Confirm.Lore");
    }
    
    public String getDenyName(final Perk.PerkType key) {
        return this.perkFiles.get(key).getString("GUI.Deny.Name");
    }
    
    public List<String> getDenyLore(final Perk.PerkType key) {
        return this.perkFiles.get(key).getStringList("GUI.Deny.Lore");
    }
    
    public String getLockedName(final Perk.PerkType key) {
        return this.perkFiles.get(key).getString("GUI.Locked.Name");
    }
    
    public List<String> getLockedLore(final Perk.PerkType key) {
        return this.perkFiles.get(key).getStringList("GUI.Locked.Lore");
    }
    
    public String getNotOwnedName(final Perk.PerkType key) {
        return this.perkFiles.get(key).getString("GUI.NotOwned.Name");
    }
    
    public List<String> getNotOwnedLore(final Perk.PerkType key) {
        return this.perkFiles.get(key).getStringList("GUI.NotOwned.Lore");
    }
    
    public String getDeactivatedName(final Perk.PerkType key) {
        return this.perkFiles.get(key).getString("GUI.Deactivated.Name");
    }
    
    public List<String> getDeactivatedLore(final Perk.PerkType key) {
        return this.perkFiles.get(key).getStringList("GUI.Deactivated.Lore");
    }
    
    public String getActivatedName(final Perk.PerkType key) {
        return this.perkFiles.get(key).getString("GUI.Activated.Name");
    }
    
    public List<String> getActivatedLore(final Perk.PerkType key) {
        return this.perkFiles.get(key).getStringList("GUI.Activated.Lore");
    }
    
    public List<String> getSelectedLore(final Perk.PerkType key) {
        return this.perkFiles.get(key).getStringList("GUI.Selected.Lore");
    }
    
    public Materials getIcon(final Perk.PerkType key) {
        return Materials.valueOf(this.perkFiles.get(key).getString("Perk.Icon"));
    }
    
    public int getRequiredLevel(final Perk.PerkType key) {
        return this.perkFiles.get(key).getInt("Perk.RequiredLevel");
    }
    
    public int getRequiredPrestige(final Perk.PerkType key) {
        return this.perkFiles.get(key).getInt("Perk.Required-Prestige");
    }
    
    public int getPrice(final Perk.PerkType key) {
        return this.perkFiles.get(key).getInt("Perk.Price");
    }
    
    public int getPriceRenown(final Perk.PerkType key) {
        return this.perkFiles.get(key).getInt("Perk.Price-Renown");
    }
    
    public FileManager get(final Perk.PerkType key) {
        return this.perkFiles.get(key);
    }
}
