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
import org.swustmc.api.SwustmcExpansion;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.swustmc.Constants.BaseConstants.*;

public final class Swustmctown extends JavaPlugin {
    @Override
    public void onEnable() {
        PLUGIN = this;
        //加载配置、语言、数据文件、papi变量
        initialize();
        //注册指令
        register_command("swustmctown",new AdminCommad());
        getLogger().info("SWUSTMCTown 已启动");
    }

    @Override
    public void onDisable() {
        getLogger().info("SWUSTMCTown 已注销");
    }

    public void register_command(String command_name,TabExecutor command_class){
        //注册指令
        Objects.requireNonNull(PLUGIN.getCommand(command_name)).setExecutor(command_class);
        //注册tab补全
        Objects.requireNonNull(PLUGIN.getCommand(command_name)).setTabCompleter(command_class);
    }

    public void load() throws IOException {
        MAIN_WORLD = Bukkit.getWorld(CONFIG.getString("mainWorld")+"");
        double[] xyz= Utils.stringToLocation(CONFIG.getString("defaultLocation")+"");
        DEFAULT_LOCATION =new Location(MAIN_WORLD,xyz[0],xyz[1],xyz[2]);
        DataDeal.loadPlayerFromFile();
    }

    public static YamlConfiguration load(String child) {
        File file = new File(PLUGIN.getDataFolder(), child);
        if (!(file.exists())) {
            PLUGIN.saveResource(child, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void initialize(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            SwustmcExpansion se = new SwustmcExpansion(PLUGIN);
            se.unregister();
            se.register();
        }
        //加载配置文件使用案例 CONFIG.getString("xxx")
        PLUGIN.saveDefaultConfig();
        CONFIG = PLUGIN.getConfig();
        //加载语音文件使用案例 Utils.getLangMsg("NORMAL")
        LANG_CONFIG = load("languages/" + CONFIG.getString("language") + ".yml");
        LangMsgApi.initLangMsg(LANG_CONFIG);
        //加载数据文件
        YML_DATAFILE =load("data.yml");
    }

    /*todo 玩家小镇
      todo 镇长支持设置小镇的bluemap的标记、小镇称号、小镇镇名
      todo PlaceholderAPI变量 （小镇称号/小镇镇名）
      todo 支持玩家添加全服bluemap标记
     */

}
