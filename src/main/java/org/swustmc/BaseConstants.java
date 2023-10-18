package org.swustmc;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.regex.Pattern;

public abstract class BaseConstants {
    public static YamlConfiguration COLOR_CONFIG;

    public static YamlConfiguration LANG_CONFIG;
    public static Swustmctown PLUGIN;
    public static FileConfiguration CONFIG;
    public static final String PLUGIN_VERSION = "0.0.1";
    public static boolean DEBUG;
    public static File DATAFILE;
    public static File INVITATIONSFILE;
    public static YamlConfiguration YML_DATAFILE;
    public static YamlConfiguration YML_INVATATIONFile;

    public static Location DEFAULT_LOCATION;
    public static World MAIN_WORLD;
    public static String PRE = "";
    public final static Pattern RPG_PATTERN = Pattern.compile("%+[a-zA-Z0-9]+%");
    public final static Pattern RPG_DEL_PATTERN = Pattern.compile("[%]");
}
