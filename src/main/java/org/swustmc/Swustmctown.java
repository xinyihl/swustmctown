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
    private static final SwustmcExpansion se = new SwustmcExpansion(PLUGIN);
    @Override
    public void onEnable() {
        PLUGIN = this;
        //加载配置、语言、数据文件、papi变量
        initialize();
        //注册指令
        registerCommand("swustmctown",new AdminCommad());
        getLogger().info("SWUSTMCTown 已启动");
    }

    @Override
    public void onDisable() {
        se.unregister();
        getLogger().info("SWUSTMCTown 已注销");
    }

    //注册插件命令
    public void registerCommand(String command_name,TabExecutor command_class){
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

    //读取yml文件，不存在则从jar里保存
    public static YamlConfiguration loadYaml(String child) {
        File file = new File(PLUGIN.getDataFolder(), child);
        if (!(file.exists())) {
            PLUGIN.saveResource(child, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    //配置、语言、数据文件、papi变量初始化
    public static void initialize(){
        //加载PlaceholderAPI 使用案例 PlaceholderAPI.setPlaceholders(player,"%player_name%");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            se.register();
        }
        //加载配置文件 使用案例 CONFIG.getString("xxx")
        PLUGIN.saveDefaultConfig();
        CONFIG = PLUGIN.getConfig();
        //加载语音文件 使用案例 Utils.getLangMsg("NORMAL")
        LANG_CONFIG = loadYaml("languages/" + CONFIG.getString("language") + ".yml");
        LangMsgApi.initLangMsg(LANG_CONFIG);
        //加载数据文件
        YML_DATAFILE =loadYaml("data.yml");
    }

    public static void reinitialize(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            se.unregister();
            se.register();
        }
        CONFIG = PLUGIN.getConfig();
        LANG_CONFIG = loadYaml("languages/" + CONFIG.getString("language") + ".yml");
        LangMsgApi.initLangMsg(LANG_CONFIG);
        YML_DATAFILE =loadYaml("data.yml");
    }

    /* todo 玩家小镇
     * todo 镇长支持设置小镇的bluemap的标记、小镇称号、小镇镇名
     * todo PlaceholderAPI变量 （小镇称号/小镇镇名）
     * todo 支持玩家添加全服bluemap标记
     */

}
