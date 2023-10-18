package org.swustmc.api;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.swustmc.Swustmctown;
import org.swustmc.Town;

import static org.swustmc.BaseConstants.PLUGIN_VERSION;

public class SwustmcExpansion extends PlaceholderExpansion {
    private Swustmctown plugin;
    public SwustmcExpansion(Swustmctown plugin) {
        this.plugin = plugin;
    }
    @Override
    public String getIdentifier() {
        return "";
    }

    @Override
    public String getAuthor() {
        return "";
    }

    @Override
    public String getVersion() {
        return PLUGIN_VERSION;
    }

    // This is required or else PlaceholderAPI will unregister the Expansion on reload
    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("town_name")){
            if(Town.isPlayer(player.getName())){
                return Town.getTownByPlayer(player.getName()).getDisplayName();
            }else{
                return "自由人";
            }
        }
        if(params.equalsIgnoreCase("town_prefix")){
            if(Town.isPlayer(player.getName())){
                return Town.getTownByPlayer(player.getName()).getPrefix();
            }else{
                return "[自由人]";
            }
        }

        return null; // Placeholder is unknown by the Expansion
    }
}
