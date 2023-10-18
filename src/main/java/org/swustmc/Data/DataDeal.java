package org.swustmc.Data;

import org.bukkit.Location;
import org.swustmc.Swustmctown;
import org.swustmc.Town;
import org.swustmc.Utils.Utils;

import java.io.IOException;

import static org.swustmc.BaseConstants.*;

public class DataDeal {
    public static void loadPlayerFromFile() throws IOException {
        for(String key: YML_DATAFILE.getKeys(false)){
            String leaderName=YML_DATAFILE.getString(key+".leaderName");
            System.out.println(leaderName);
            String displayName=YML_DATAFILE.getString(key+".displayName");
            double[] xyz= Utils.stringToLocation(YML_DATAFILE.getString(key+".location")+"");
            Location location=new Location(MAIN_WORLD,xyz[0],xyz[1],xyz[2]);
            Town town=new Town(leaderName,key,displayName ,location);
            town.setPlayer(YML_DATAFILE.getStringList(key+".playerName"));
        }
    }
    public static void saveConfig() throws IOException {
        YML_DATAFILE.save(DATAFILE);
    }
    public static void saveTown(Town town) throws IOException {
        YML_DATAFILE.set(town.getName()+".displayName",town.getDisplayName());
        YML_DATAFILE.set(town.getName()+".leaderName",town.getLeaderName());
        YML_DATAFILE.set(town.getName()+".location",Utils.locationToString(town.getPoint()));
        YML_DATAFILE.set(town.getName()+".playerName",town.getCitizens());


    }
    public static void saveAll() throws IOException {
        for(Town town:Swustmctown.towns){
            saveTown(town);
        }
        saveConfig();
    }
}

