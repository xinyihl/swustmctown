package org.swustmc.api;

import org.bukkit.configuration.file.YamlConfiguration;
import org.swustmc.BaseConstants;

public class LangMsgApi {

    public static void initLangMsg(YamlConfiguration langConfig) {
        BaseConstants.LANG_CONFIG = langConfig;
    }

}
