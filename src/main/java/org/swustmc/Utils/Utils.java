package org.swustmc.Utils;

import org.bukkit.Location;

public class Utils {
    public static double[] stringToLocation(String s){
        double[] xyz = new double[2];
        String[] ss=s.split(",");
        xyz[0]=Double.parseDouble(ss[0]);
        xyz[1]=Double.parseDouble(ss[1]);
        xyz[2]=Double.parseDouble(ss[2]);
        return xyz;
    }
    public static String locationToString(Location loc){
        return loc.getX()+","+loc.getY()+","+loc.getZ();
    }
}
