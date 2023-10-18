package org.swustmc.Data;

import org.bukkit.configuration.file.YamlConfiguration;
import org.swustmc.BaseConstants;
import org.swustmc.Swustmctown;
import org.swustmc.Town;
import org.swustmc.invitation.Invitation;

import java.io.IOException;

//HashMap读取存储
public class InvitationDeal {
    static YamlConfiguration config=BaseConstants.YML_INVATATIONFile;
    public static void saveInvitation() throws IOException {
        for(String key:config.getKeys(false)){
            config.set(key,null);
        }
        for(Invitation inv:Swustmctown.invatations){
            config.set(inv.getResponsor(),inv.getInvitor().getName());

        }
        config.save(BaseConstants.INVITATIONSFILE);


    }
    public static void importInvitation(){

        for(String key:config.getKeys(false)){
            new Invitation(Town.getTownByName(config.getString(key)),key);
        }
    }

}
