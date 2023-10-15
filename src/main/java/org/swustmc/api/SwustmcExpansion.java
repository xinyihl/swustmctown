package org.swustmc.api;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.swustmc.Swustmctown;

import static org.swustmc.Constants.BaseConstants.*;

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
        if(params.equalsIgnoreCase("placeholder1")){
            return CONFIG.getString("placeholders.placeholder1", "default1");
        }

        if(params.equalsIgnoreCase("placeholder2")) {
            return CONFIG.getString("placeholders.placeholder2", "default2");
        }

        return null; // Placeholder is unknown by the Expansion
    }
}
