package org.swustmc.invitation;

import org.bukkit.Bukkit;
import org.swustmc.Swustmctown;
import org.swustmc.Town;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Invitation {
    private Town invitor;
    //回应邀请的人的name
    private String responsor;

    public Town getInvitor() {
        return invitor;
    }

    public String getResponsor() {
        return responsor;
    }

    public Invitation(Town invitor, String responsor) {
        boolean has=false;
        Invitation invitation = null;
        for(Invitation inv:Swustmctown.invatations){
            if(inv.getInvitor().getName().equalsIgnoreCase(responsor)){
                has=true;
                Bukkit.getLogger().info("已经拥有");
            }
        }
        if(!has){
            this.invitor = invitor;
            this.responsor = responsor;
            Swustmctown.invatations.add(this);
        }

    }
    //标记为已处理邀请请求,将其移除
    public void processed(){
        Swustmctown.invatations.remove(this);
    }

    public static List<Town> getTownsByPlayer(String name){
        List<Town> towns = new ArrayList<Town>();
        for(Invitation inv:Swustmctown.invatations){
            if(inv.responsor.equals(name)){
                towns.add(inv.invitor);
            }

        }
        return towns;
    }
    public void accept() throws IOException {
        invitor.addPlayer(responsor);
        processed();
    }

}
