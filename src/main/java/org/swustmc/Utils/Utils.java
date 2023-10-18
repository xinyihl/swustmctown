package org.swustmc.Utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.swustmc.BaseConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

public class Utils {
    public static double[] stringToLocation(String s){
        double[] xyz = new double[3];
        String[] ss=s.split(",");
        xyz[0]=Double.parseDouble(ss[0]);
        xyz[1]=Double.parseDouble(ss[1]);
        xyz[2]=Double.parseDouble(ss[2]);
        return xyz;
    }

    public static String locationToString(Location loc){

        return loc.getX()+","+loc.getY()+","+loc.getZ();
    }
    /**
     * 判断是否为玩家
     *
     * @param sender 发送者
     * @return true是
     */
    public static Boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    /**
     * 获取在线玩家
     *
     * @param sender     控制台
     * @param playerName 玩家名
     * @return Player
     * @since 3.2.1
     */
    public static Player getOnlinePlayer(CommandSender sender, String playerName) {
        if (StrUtil.isEmpty(playerName)) {
            return (Player) sender;
        }
        return getOnlinePlayer(playerName);
    }

    /**
     * 获取在线玩家
     *
     * @param playerName 玩家名
     * @return Player
     * @since 3.2.2
     */
    public static Player getOnlinePlayer(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null || !player.isOnline()) {
            return null;
        }
        return player;
    }

    /**
     * 获取在线玩家
     *
     * @param playerUuid 玩家UUID
     * @return Player
     * @since 3.2.2
     */
    public static Player getOnlinePlayer(UUID playerUuid) {
        Player player = Bukkit.getPlayer(playerUuid);
        if (player == null || !player.isOnline()) {
            return null;
        }
        return player;
    }

    /**
     * 判断是否为玩家
     *
     * @param sender 发送者
     * @return true否
     */
    public static Boolean isNotPlayer(CommandSender sender) {
        return !isPlayer(sender);
    }

    /**
     * 颜色代码转换
     *
     * @param str 消息
     * @return 转换后的字符串
     */
    public static String replaceChatColor(String str) {
        return replaceChatColor(str, true);
    }

    /**
     * 颜色代码转换
     *
     * @param stringList 消息
     * @return 转换后的字符串集合
     * @since 2.4.5
     */
    public static List<String> replaceChatColor(List<String> stringList) {
        return replaceChatColor(stringList, true);
    }

    /**
     * 颜色代码转换
     *
     * @param stringList 消息
     * @param isRpg      是否rpg消息
     * @return 转换后的字符串集合
     */
    public static List<String> replaceChatColor(List<String> stringList, boolean isRpg) {
        List<String> loreList = new ArrayList<>();
        if (CollUtil.isEmpty(stringList)) {
            return loreList;
        }
        for (String lore : stringList) {
            loreList.add(replaceChatColor(lore, isRpg));
        }
        return loreList;
    }

    /**
     * 颜色代码转换
     *
     * @param str   消息
     * @param isRpg 是否rpg消息
     * @return 转换后的字符串
     */
    public static String replaceChatColor(String str, boolean isRpg) {
        if (StrUtil.isEmpty(str)) {
            return "";
        }
        String newStr = str.replace("&", "§");
        return isRpg ? replaceRpgChatColor(newStr) : newStr;
    }

    /**
     * rpg颜色转换
     *
     * @param str 字符串
     * @return str
     */
    public static String replaceRpgChatColor(String str) {
        if (StrUtil.isEmpty(str)) {
            return "";
        }
        if (BaseConstants.COLOR_CONFIG == null) {
            return str;
        }
        Matcher matcher = BaseConstants.RPG_PATTERN.matcher(str);
        List<String> matchStrList = new ArrayList<>();
        while (matcher.find()) {
            matchStrList.add(matcher.group());
        }
        if (CollUtil.isEmpty(matchStrList)) {
            return str;
        }
        for (String value : matchStrList) {
            String rpgStr = BaseConstants.COLOR_CONFIG.getString(stringFilter(value));
            if (StrUtil.isEmpty(rpgStr) || rpgStr.length() != 6) {
                continue;
            }
            ChatColor chatColor = ChatColor.of("#" + rpgStr);
            str = str.replace(value, chatColor + "");
        }
        return str;
    }

    /**
     * 过滤特殊字符 %
     *
     * @param str 变量
     * @return str
     */
    public static String stringFilter(String str) {
        return BaseConstants.RPG_DEL_PATTERN.matcher(str).replaceAll("").trim();
    }

    /**
     * 清理颜色代码
     *
     * @param str 字符串
     * @return 新字符串
     * @since 3.4.0
     */
    public static String stripColor(String str) {
        return ChatColor.stripColor(Utils.replaceChatColor(str));
    }


    /**
     * 获取语言文件中的配置
     *
     * @param langMsg 配置项
     * @return 语言
     */
    public static String getLangMsg(String langMsg) {
        FileConfiguration langConfig = BaseConstants.LANG_CONFIG;
        if (langConfig == null) {
            return "§4加载语言文件出错,请重新加载!";
        }
        String msg = langConfig.getString(langMsg);
        return msg != null ? replaceChatColor(msg) : "§4语言文件中未找到该配置项:" + langMsg + "§a,请删除现在语言文件让重新生成";
    }

    /**
     * 获取语言文件中的配置
     *
     * @param langMsg    配置项
     * @param defaultMsg 默认语言
     * @return 语言
     */
    public static String getLangMsg(String langMsg, String defaultMsg) {
        FileConfiguration langConfig = BaseConstants.LANG_CONFIG;
        if (langConfig == null) {
            return "§4加载语言文件出错,请重新加载!";
        }
        String msg = langConfig.getString(langMsg);
        return msg != null ? replaceChatColor(msg) : defaultMsg;
    }
}
