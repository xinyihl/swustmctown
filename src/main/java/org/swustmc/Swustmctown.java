package org.swustmc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.swustmc.Commad.AdminCommad;
import org.swustmc.Date.DataDeal;
import org.swustmc.Utils.Utils;
import org.swustmc.api.LangMsgApi;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.swustmc.Constants.BaseConstants.*;

public final class Swustmctown extends JavaPlugin {
    @Override
    public void onEnable() {
        plugin = this;
        //加载配置、语言、数据文件
        loadconfig();
        //注册指令
        registercommand("swustmctown",new AdminCommad());
        getLogger().info("SWUSTMCTown 已启动");
    }

    @Override
    public void onDisable() {
        getLogger().info("SWUSTMCTown 已注销");
    }

    public void registercommand(String commandname,TabExecutor commandclass){
        //注册指令
        Objects.requireNonNull(plugin.getCommand(commandname)).setExecutor(commandclass);
        //注册tab补全
        Objects.requireNonNull(plugin.getCommand(commandname)).setTabCompleter(commandclass);
    }

    public void load() throws IOException {
        mainWorld= Bukkit.getWorld(CONFIG.getString("mainWorld")+"");
        double[] xyz= Utils.stringToLocation(CONFIG.getString("defaultLocation")+"");
        defaultLocation=new Location(mainWorld,xyz[0],xyz[1],xyz[2]);
        DataDeal.loadPlayerFromFile();
    }

    public static YamlConfiguration load(String child) {
        File file = new File(plugin.getDataFolder(), child);
        if (!(file.exists())) {
            plugin.saveResource(child, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void loadconfig(){
        //加载配置文件使用案例 CONFIG.getString("xxx")
        plugin.saveDefaultConfig();
        CONFIG = plugin.getConfig();
        //加载语音文件使用案例 Utils.getLangMsg("NORMAL")
        LANG_CONFIG = load("languages/" + CONFIG.getString("language") + ".yml");
        LangMsgApi.initLangMsg(LANG_CONFIG);
        //加载数据文件
        dataFileM =load("data.yml");
    }

    /*todo 玩家小镇
      todo 镇长支持设置小镇的bluemap的标记、小镇称号、小镇镇名
      todo PlaceholderAPI变量 （小镇称号/小镇镇名）
      todo 支持玩家添加全服bluemap标记
     */

}
