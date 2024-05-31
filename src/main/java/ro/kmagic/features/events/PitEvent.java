package ro.kmagic.features.events;

import org.bukkit.entity.Player;

public abstract class PitEvent {
    public abstract void preStartEvent();
    
    public abstract String getDescription();
    
    public void addTeam(final Player player) {
    }
    
    public void removeTeam(final Player player) {
    }
    
    public void giveHead(final Player player) {
    }
    
    public boolean isBeast(final Player player) {
        return false;
    }
    
    public void selectRandomBeast() {
    }
    
    public int getRagePitDamageDealt(final Player player) {
        return 0;
    }
    
    public void addRagePitKill() {
    }
    
    public int getRagePitKills() {
        return 0;
    }
    
    public int getTeamDeathmatchKAndA(final Player player) {
        return 0;
    }
    
    public int getTeamDeathmatchBlueKills() {
        return 0;
    }
    
    public int getTeamDeathmatchRedKills() {
        return 0;
    }
    
    public String getTeamColorCode(final Player player) {
        return "&7";
    }
    
    public int getTheBeastKills(final Player player) {
        return 0;
    }
    
    public int getTheBeastMaxLivingTime() {
        return 0;
    }
    
    public void playerQuit(final Player player) {
    }
}
