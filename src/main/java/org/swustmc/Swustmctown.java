package org.swustmc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.swustmc.Commad.AdminCommand;
import org.swustmc.Commad.PlayerCommand;
import org.swustmc.Data.DataDeal;
import org.swustmc.Data.InvitationDeal;
import org.swustmc.Utils.Utils;
import org.swustmc.api.LangMsgApi;
import org.swustmc.api.SwustmcExpansion;
import org.swustmc.invitation.Invitation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.swustmc.BaseConstants.*;

public final class Swustmctown extends JavaPlugin {
    public static List<Town> towns=new ArrayList<Town>();
    public static List<Invitation> invatations= new ArrayList<Invitation>();
    private static final SwustmcExpansion se = new SwustmcExpansion(PLUGIN);
    @Override
    public void onEnable() {
        PLUGIN = this;
        //加载配置、语言、数据文件、papi变量
        try {
            initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new SwustmcExpansion(this).register();
        //注册指令
        Objects.requireNonNull(getCommand("swustmctown")).setExecutor(new AdminCommand());
        Objects.requireNonNull(getCommand("swustmctown")).setExecutor(new PlayerCommand());
        Objects.requireNonNull(getCommand("swustmctown")).setTabCompleter(new AdminCommand());
        Objects.requireNonNull(getCommand("swustmctown")).setTabCompleter(new PlayerCommand());
        getLogger().info("SWUSTMCTown 已启动");
    }

    @Override
    public void onDisable() {
        se.unregister();
        try {
            DataDeal.saveAll();
            InvitationDeal.saveInvitation();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getLogger().info("SWUSTMCTown 已注销");
    }

    public void load() throws IOException {
        MAIN_WORLD = Bukkit.getWorld(CONFIG.getString("mainWorldName"));
        double[] xyz= Utils.stringToLocation(CONFIG.getString("defaultLocation"));
        DEFAULT_LOCATION =new Location(MAIN_WORLD,xyz[0],xyz[1],xyz[2]);
        PRE=getConfig().getString("pre").replace("§","&");
        DataDeal.loadPlayerFromFile();
    }

    //读取yml文件，不存在则从jar里保存
    public static YamlConfiguration loadYaml(String child) {

        //反射保存file
        File file = new File(PLUGIN.getDataFolder(), child);
        if(!child.contains("zh_cn")) {
            Field fileR = null;
            try {
                System.out.println(BaseConstants.class.getDeclaredFields().toString());
                fileR = BaseConstants.class.getDeclaredField(child.toUpperCase().replace(".YML","") + "FILE");
                fileR.set(null, file);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (!(file.exists())) {
            PLUGIN.saveResource(child, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    //配置、语言、数据文件、papi变量初始化
    public static void initialize() throws IOException {
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
        YML_INVATATIONFile=loadYaml("invitations.yml");

        DataDeal.loadPlayerFromFile();
        InvitationDeal.importInvitation();
    }

    public static void reinitialize() throws IOException {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            se.unregister();
            se.register();
        }
        CONFIG = PLUGIN.getConfig();
        LANG_CONFIG = loadYaml("languages/" + CONFIG.getString("language") + ".yml");
        LangMsgApi.initLangMsg(LANG_CONFIG);
        YML_DATAFILE =loadYaml("data.yml");
        YML_INVATATIONFile=loadYaml("invitations.yml");
        DataDeal.loadPlayerFromFile();
        InvitationDeal.importInvitation();
    }

    /* todo 玩家小镇
     * todo 镇长支持设置小镇的bluemap的标记、小镇称号、小镇镇名
     * todo PlaceholderAPI变量 （小镇称号/小镇镇名）
     * todo 支持玩家添加全服bluemap标记
     */

}
