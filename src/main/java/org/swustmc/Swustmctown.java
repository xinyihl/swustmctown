package org.swustmc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.swustmc.Commad.AdminCommad;
import org.swustmc.Date.DataDeal;
import org.swustmc.Model.Town;
import org.swustmc.Utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class Swustmctown extends JavaPlugin {
    public static Swustmctown plugin;
    public static final String plugin_version = "0.0.1";
    public static boolean is_debug;
    public static File dataFile;
    public static YamlConfiguration dataFileM;
    public static List<Town> towns;
    public static Location defaultLocation;
    public static World mainWorld;

    @Override
    public void onEnable() {
        plugin = this;
        //加载配置文件使用案例 plugin.getConfig().getString("xxx")
        plugin.saveDefaultConfig();
        plugin.getConfig();
        //加载数据文件
        dataFile=new File(getDataFolder(),"data.yml");
        if(!dataFile.exists()){
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        dataFileM =YamlConfiguration.loadConfiguration(dataFile);
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
        mainWorld= Bukkit.getWorld(plugin.getConfig().getString("mainWorld")+"");
        double[] xyz= Utils.stringToLocation(plugin.getConfig().getString("defaultLocation")+"");
        defaultLocation=new Location(mainWorld,xyz[0],xyz[1],xyz[2]);
        DataDeal.loadPlayerFromFile();
    }

    /*todo 玩家小镇
      todo 镇长支持设置小镇的bluemap的标记、小镇称号、小镇镇名
      todo PlaceholderAPI变量 （小镇称号/小镇镇名）
      todo 支持玩家添加全服bluemap标记
     */

}
