package org.swustmc.Constants;

import org.bukkit.Location;
import org.bukkit.World;
import java.util.regex.Pattern;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.swustmc.Model.Town;
import org.swustmc.Swustmctown;

import java.io.File;
import java.util.List;

public abstract class BaseConstants {
    public static YamlConfiguration COLOR_CONFIG;
    public static YamlConfiguration LANG_CONFIG;
    public static YamlConfiguration STORAGE_CONFIG;
    public static Swustmctown plugin;
    public static FileConfiguration CONFIG;
    public static final String plugin_version = "0.0.1";
    public static boolean is_debug;
    public static File dataFile;
    public static YamlConfiguration dataFileM;
    public static List<Town> towns;
    public static Location defaultLocation;
    public static World mainWorld;
    public final static Pattern NUMERIC = Pattern.compile("^-?\\d+(\\.\\d+)?$");
    public static final Pattern BRACKET_NUMBER = Pattern.compile("\\[(\\d+)\\]");
    public final static Pattern RPG_PATTERN = Pattern.compile("%+[a-zA-Z0-9]+%");
    public final static Pattern RPG_DEL_PATTERN = Pattern.compile("[%]");
    @Deprecated
    public final static Pattern INTENSIFY_PATTERN = Pattern.compile("§f\\[\\+§a+[0-9]+§f\\]");
    public final static Pattern INTENSIFY_LEVEL_PATTERN = Pattern.compile("\\++[0-9]+§f\\]");
    public final static Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    public final static Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
}
