package org.swustmc.Commad;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.swustmc.BaseConstants;
import org.swustmc.Swustmctown;
import org.swustmc.Town;
import org.swustmc.Utils.TabList;
import org.swustmc.invitation.Invitation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerCommand implements TabExecutor {
    /*
     * swustmctown create [townName] [disPlayName] 创建小镇,默认值为defaultLoc
     * swustmctown setloc 设置地点
     * swustmctown prefix [playerPrefix] 玩家前缀
     * swustmctown invite [playerName]  邀请玩家加入自己小镇
     * swustmctown accept [townName]同意邀请
     * swustmctown quit 退出/解散队伍
     * swustmctown info 查看邀请自己进入小镇的小镇列表
     * TODO
     *  进入MC的消息留言
     *  BlueMap兼容
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
                        Town town=new Town(sender.getName(),args[1],args[2]);
                        Swustmctown.towns.add(town);
                        try {
                            town.addPlayer(sender.getName());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        sender.sendMessage(BaseConstants.PRE+"创建成功一个名为"+args[1]+"的小镇,你可以使用/swustmctown setLoc指令设置现在所处位置为小镇标记点");
                        sender.sendMessage(BaseConstants.PRE+"你可以使用/swustmctown prefix [玩家前缀名] 进行设置小镇成员前缀");
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

            }else if(args[0].equalsIgnoreCase("invite")) {
                if (!sender.hasPermission("swustmc.invite")) {
                    sender.sendMessage(BaseConstants.PRE + "你没有使用该指令的权限");
                    return false;
                } else if (!Town.isLeader(sender.getName())) {
                    sender.sendMessage(BaseConstants.PRE + "你不是小镇领导者,不能使用这个指令");
                    return false;
                }else{
                    new Invitation(Town.getTownByLeader(sender.getName()), args[1]);
                    sender.sendMessage(BaseConstants.PRE+"邀请已发送");
                    return true;
                }
            }else if(args[0].equalsIgnoreCase("info")){
                List<String> l= new ArrayList<String>();
                l.add(BaseConstants.PRE+"=================================\n");
                for(Town town:Invitation.getTownsByPlayer(sender.getName())){

                    l.add(BaseConstants.PRE+"小镇名称"+town.getName()+"    小镇镇长"+town.getLeaderName()+"\n");
                }
                l.add(BaseConstants.PRE+"=================================");
                for(String str:l){
                    sender.sendMessage(str);
                }
                return true;
            }else if(args[0].equalsIgnoreCase("accept")){
                if (!sender.hasPermission("swustmc.accept")) {
                    sender.sendMessage(BaseConstants.PRE + "你没有使用该指令的权限");
                    return false;
                } else if(Town.isPlayer(sender.getName())){
                    sender.sendMessage(BaseConstants.PRE+"你已经加入了一个小镇,请退出后再进入其他小镇");
                    return false;
                }
                String townName=args[1];
                boolean has=false;
                Invitation invitation = null;
                for(Invitation inv:Swustmctown.invatations){
                    if(inv.getInvitor().getName().equalsIgnoreCase(townName)){
                        has=true;
                        invitation=inv;
                        try {
                            invitation.accept();
                            sender.sendMessage(BaseConstants.PRE+"你成功加入该小镇");
                            return true;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if(!has){
                    sender.sendMessage(BaseConstants.PRE+"你没有接收到这个小镇的邀请");
                    return false;
                }
            } else if (args[0].equalsIgnoreCase("quit")) {
                if(!Town.isPlayer(sender.getName())){
                    sender.sendMessage(BaseConstants.PRE+"你没有加入一个小镇");
                    return false;
                }else{
                    if(Town.isLeader(sender.getName())){
                        Swustmctown.towns.remove(Town.getTownByLeader(sender.getName()));
                        sender.sendMessage(BaseConstants.PRE+"你已经成功解散这个小镇");
                        return true;
                    }else{
                        Town town =Town.getTownByPlayer(sender.getName());
                        town.getCitizens().remove(sender.getName());
                        sender.sendMessage(BaseConstants.PRE+"你已经成功退出该小镇");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return TabList.returnList(strings,strings.length,commandSender);
    }
}
