package org.swustmc.Date;

import org.bukkit.Location;
import org.swustmc.Swustmctown;
import org.swustmc.Model.Town;
import org.swustmc.Utils.Utils;

import java.io.IOException;

public class DataDeal {
    public static void loadPlayerFromFile() throws IOException {
        for(String key: Swustmctown.dataFileM.getKeys(false)){
            double[] xyz= Utils.stringToLocation(Swustmctown.dataFileM.getString(key+".location"));
            Town town=new Town(Swustmctown.dataFileM.getString(key+".leaderName"),key,Swustmctown.dataFileM.getString(key+".displayName"),new Location(Swustmctown.mainWorld,xyz[0],xyz[1],xyz[2]));
            town.setPlayer(Swustmctown.dataFileM.getStringList(key+".playerName"));
        }
    }
    public static void saveConfig() throws IOException {
        Swustmctown.dataFileM.save(Swustmctown.dataFile);
    }
    public static void saveTown(Town town) throws IOException {
        Swustmctown.dataFileM.set(town.getName()+".displayName",town.getDisplayName());
        Swustmctown.dataFileM.set(town.getName()+".leaderName",town.getLeaderName());
        Swustmctown.dataFileM.set(town.getName()+".location",town.getPoint());
        Swustmctown.dataFileM.set(town.getName()+".playerName",town.getCitizens());
        saveConfig();

    }
}
