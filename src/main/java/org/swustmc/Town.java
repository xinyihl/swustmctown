package org.swustmc;

import org.bukkit.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Town {
    private List<String> citizens=new ArrayList<String>();
    private Location point;
    private  String name;
    private String displayName;
    private String leaderName;
    private  String prefix="";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Town(String leaderName, String name, String displayName){
        this.leaderName=leaderName;
        this.displayName=displayName;
        this.name=name;
        this.point=BaseConstants.DEFAULT_LOCATION;
        System.out.println(point);
        citizens.add(leaderName);
        Swustmctown.towns.add(this);
    }
    public Town(String leaderName, String name, String displayName,Location loc){
        this.leaderName=leaderName;
        this.displayName=displayName;
        this.name=name;
        this.point=loc;
        citizens.add(leaderName);
        Swustmctown.towns.add(this);
    }
    public void addPlayer(String name) throws IOException {

        if(!citizens.contains(name)){
            citizens.add(name);
        }

    }
    public void setPlayer(List<String> playerNames) throws IOException {
        citizens=playerNames;

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
    public static Town getTownByPlayer(String playerName){
        if(!isPlayer(playerName)){
            return null;
        }
        for(Town town:Swustmctown.towns){
            if(town.getCitizens().contains(playerName)){
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
    public static Town getTownByName(String name){
        for(Town town:Swustmctown.towns){
            return town;
        }
        return null;
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
