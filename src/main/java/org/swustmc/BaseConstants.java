package org.swustmc;

import org.bukkit.Location;
import org.bukkit.World;
import java.util.regex.Pattern;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.swustmc.Swustmctown;

import java.io.File;

public abstract class BaseConstants {
    public static YamlConfiguration COLOR_CONFIG;

    public static YamlConfiguration LANG_CONFIG;
    public static Swustmctown PLUGIN;
    public static FileConfiguration CONFIG;
    public static final String PLUGIN_VERSION = "0.0.1";
    public static boolean DEBUG;
    public static File DATAFILE;
    public static File INVATATIONFILE;
    public static YamlConfiguration YML_DATAFILE;
    public static YamlConfiguration YML_INVATATIONFile;

    public static Location DEFAULT_LOCATION;
    public static World MAIN_WORLD;
    public static String PRE = "";
    public final static Pattern NUMERIC = Pattern.compile("^-?\\d+(\\.\\d+)?$");
    public static final Pattern BRACKET_NUMBER = Pattern.compile("\\[(\\d+)\\]");
    public final static Pattern RPG_PATTERN = Pattern.compile("%+[a-zA-Z0-9]+%");
    public final static Pattern RPG_DEL_PATTERN = Pattern.compile("[%]");
    public final static Pattern INTENSIFY_PATTERN = Pattern.compile("§f\\[\\+§a+[0-9]+§f\\]");
    public final static Pattern INTENSIFY_LEVEL_PATTERN = Pattern.compile("\\++[0-9]+§f\\]");
    public final static Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    public final static Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
}
