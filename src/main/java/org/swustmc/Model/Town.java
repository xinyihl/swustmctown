package org.swustmc.Model;

import org.bukkit.Location;
import org.swustmc.Constants.BaseConstants;
import org.swustmc.Data.DataDeal;
import org.swustmc.Swustmctown;


import java.io.IOException;
import java.util.List;

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
        Swustmctown.towns.add(this);
    }
    public void addPlayer(String name) throws IOException {
        citizens.add(name);
        BaseConstants.YML_DATAFILE.set(name+".playerName",citizens);
        DataDeal.saveConfig();
    }
    public void setPlayer(List<String> playerNames) throws IOException {
        citizens=playerNames;
        BaseConstants.YML_DATAFILE.set(name+".playerName",citizens);
        try {
            DataDeal.saveConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPoint(Location point) {
        this.point = point;
    }

    public static Town getTownByLeader(String leaderName){
        if(!isLeader(leaderName)){
            return null;
        }
        for(Town town:Swustmctown.towns){
            if(town.getLeaderName().equals(leaderName)){
                return town;
            }
        }
        return null;
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
    public static boolean isLeader(String name){
        for(Town town: Swustmctown.towns){
            if(town.getLeaderName().equals(name)){
                return true;
            }
        }
        return false;
    }public static boolean isPlayer(String name){
        for(Town town:Swustmctown.towns){
            for(String playerName:town.getCitizens()){
                if(playerName.equals(name)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isExist(String townName){
        for(Town town:Swustmctown.towns){
            if(town.getName().equals(townName)){
                return true;
            }
        }
        return false;
    }
}
