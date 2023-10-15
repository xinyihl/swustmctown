package org.swustmc.Model;

import org.bukkit.Location;
import org.swustmc.Date.DataDeal;

import java.io.IOException;
import java.util.List;

import static org.swustmc.Constants.BaseConstants.YML_DATAFILE;
import static org.swustmc.Constants.BaseConstants.TOWNS;

public class Town {
    private List<String> citizens;
    private Location point;
    private  String name;
    private String displayName;
    private String leaderName;
    public Town(String leaderName,String name,String displayName,Location point){
        this.leaderName=leaderName;
        this.displayName=displayName;
        this.name=name;
        this.point=point;
        citizens.add(leaderName);
        TOWNS.add(this);
    }
    public void addPlayer(String name) throws IOException {
        citizens.add(name);
        YML_DATAFILE.set(name+".playerName",citizens);
        DataDeal.saveConfig();
    }
    public void setPlayer(List<String> playerNames) throws IOException {
        citizens=playerNames;
        YML_DATAFILE.set(name+".playerName",citizens);
        try {
            DataDeal.saveConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getCitizens() {
        return citizens;
    }

    public Location getPoint() {
        return point;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLeaderName() {
        return leaderName;
    }
}
