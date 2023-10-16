package org.swustmc.Commad;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.swustmc.BaseConstants;
import org.swustmc.Town;
import org.swustmc.Swustmctown;

import java.io.IOException;
import java.util.List;

public class PlayerCommand implements TabExecutor {
    /*
     * swustmctown create [townName] [disPlayName] 创建小镇,默认值为defaultLoc
     * swustmctown setloc 设置地点
     * swustmctown prefix [playerPrefix] 玩家前缀
     * swustmctown invite [playerName]  邀请玩家加入自己小镇
     * swustmctown accept 同意邀请
     * swustmctown quit 退出/解散队伍
     *     * */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("你不是一个有效玩家");
            return false;
        }
        Player sender=(Player) commandSender;
        if(s.equals("swustmctown")){
            if(args[0].equalsIgnoreCase("create")  ){
                if(!sender.hasPermission("swustmctown.create")){
                    sender.sendMessage(BaseConstants.PRE+"你没有权限");
                    return false;
                }else if(Town.isLeader(sender.getName())){
                    sender.sendMessage(BaseConstants.PRE+"你已经是一个小镇镇长，你可解散后再进行创建");
                    return false;
                }else if(Town.isPlayer(sender.getName())){
                    sender.sendMessage(BaseConstants.PRE+"你已经加入一个小镇,你可以退出后再进行创建");
                    return false;
                }else{

                    String townName=args[1];
                    if(Town.isExist(townName)){
                        sender.sendMessage(BaseConstants.PRE+"该小镇已经存在,请换一个名字");
                        return false;
                    }else{
                        String displayName=args[2];
                        Town town=new Town(sender.getName(),args[1],args[2],BaseConstants.DEFAULT_LOCATION);
                        Swustmctown.towns.add(town);
                        try {
                            town.addPlayer(sender.getName());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        sender.sendMessage(BaseConstants.PRE+"创建成功一个名为"+args[1]+"的小镇,你可以使用/swustmctown setLoc指令设置现在所处位置为小镇标记点");
                        sender.sendMessage(BaseConstants.PRE+"你可以使用/BaseConstants.PREfix [玩家前缀名] 进行设置小镇成员前缀");
                        return true;
                    }
                }
            }else if(args[0].equalsIgnoreCase("setLoc")){
                if(!sender.hasPermission("swustmc.setloc")){
                    sender.sendMessage(BaseConstants.PRE+"你没有使用该指令的权限");
                    return false;
                }else if(!Town.isLeader(sender.getName())){
                    sender.sendMessage(BaseConstants.PRE+"你不是小镇领导者,不能使用这个指令");
                    return false;
                }else{
                    Town town=Town.getTownByLeader(sender.getName());
                    town.setPoint(sender.getLocation());
                    sender.sendMessage(BaseConstants.PRE+"成功设置地点");
                    return true;
                }
            }else if(args[0].equalsIgnoreCase("prefix")){
                if(!sender.hasPermission("swustmc.prefix")){
                    sender.sendMessage(BaseConstants.PRE+"你没有使用该指令的权限");
                    return false;
                }else if(!Town.isLeader(sender.getName())){
                    sender.sendMessage(BaseConstants.PRE+"你不是小镇领导者,不能使用这个指令");
                    return false;
                }else if((!(args[1].length()<=10))){
                    sender.sendMessage(BaseConstants.PRE+"前缀长度不得高于10个字符串");
                    return false;
                }else {
                    Town.getTownByLeader(sender.getName()).setPrefix(args[1]);
                    sender.sendMessage(BaseConstants.PRE+"成功设置前缀");
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
