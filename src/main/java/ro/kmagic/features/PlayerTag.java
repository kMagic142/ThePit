package ro.kmagic.features;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import ro.kmagic.Main;

public class PlayerTag implements Listener {
    private final String beastTag;
    private final String bountyPlayerTag;
    
    public PlayerTag() {
        String tag = "-T";
        this.beastTag = Main.getPitEventManager().getString("Event.Major.THE_BEAST.Tag");
        this.bountyPlayerTag = Main.getMessages().getString("Bounty.Tag");
    }
    
    public String getFormattedPrefix(final Player player) {
        return Main.getXpTag().getScoreboard(player);
    }
    
    public String getFormattedSuffix(final Player player) {
        if (Main.getPitEventManager().isBeast(player)) {
            return " " + this.beastTag;
        }
        if (Main.getBounty().getBounty(player) != 0) {
            return " " + this.bountyPlayerTag.replace("%gold%", String.valueOf(Main.getBounty().getBounty(player)));
        }
        return "";
    }

}
