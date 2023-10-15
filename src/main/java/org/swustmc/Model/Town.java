package org.swustmc.Model;

import org.bukkit.Location;
import org.swustmc.Date.DataDeal;

import java.io.IOException;
import java.util.List;

import static org.swustmc.Constants.BaseConstants.YML_DATAFILE;
import static org.swustmc.Constants.BaseConstants.TOWNS;

public class Town {
    private List<String> citizens;
    private Location point; //小镇坐标
    private String name; //yml存储键名
    private String displayName; //显示名称
    private String leaderName; //镇长
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
    public void removePlayer(String name) throws IOException {
        //todo removePlayer
    }
    public void setPlayer(List<String> playerNames) throws IOException {
        citizens=playerNames;
        YML_DATAFILE.set(name+".playerName",citizens);
        DataDeal.saveConfig();
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
