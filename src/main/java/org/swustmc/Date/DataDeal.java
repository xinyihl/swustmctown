package org.swustmc.Date;

import org.bukkit.Location;
import org.swustmc.Swustmctown;
import org.swustmc.Model.Town;
import org.swustmc.Utils.Utils;

import java.io.IOException;

import static org.swustmc.Constants.BaseConstants.*;

public class DataDeal {
    public static void loadPlayerFromFile() throws IOException {
        for(String key: dataFileM.getKeys(false)){
            double[] xyz= Utils.stringToLocation(dataFileM.getString(key+".location")+"");
            Town town=new Town(dataFileM.getString(key+".leaderName"),key,dataFileM.getString(key+".displayName"),new Location(mainWorld,xyz[0],xyz[1],xyz[2]));
            town.setPlayer(dataFileM.getStringList(key+".playerName"));
        }
    }
    public static void saveConfig() throws IOException {
        dataFileM.save(dataFile);
    }
    public static void saveTown(Town town) throws IOException {
        dataFileM.set(town.getName()+".displayName",town.getDisplayName());
        dataFileM.set(town.getName()+".leaderName",town.getLeaderName());
        dataFileM.set(town.getName()+".location",town.getPoint());
        dataFileM.set(town.getName()+".playerName",town.getCitizens());
        saveConfig();

    }
}
