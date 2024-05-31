package ro.kmagic.libapi.scoreboard;

import org.bukkit.scoreboard.Team;

public class ScoreboardLine
{
    private final Team team;
    private final String line;
    private String entry;
    private final int row;
    
    public ScoreboardLine(final Team team, final String line, final int row) {
        this.team = team;
        this.line = line;
        this.row = row;
    }
    
    public Team getTeam() {
        return this.team;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public String getLine() {
        return this.line;
    }
    
    public void setEntry(final String entry) {
        this.entry = entry;
    }
    
    public String getEntry() {
        return this.entry;
    }
}
