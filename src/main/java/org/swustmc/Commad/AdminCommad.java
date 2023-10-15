package org.swustmc.Commad;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.swustmc.Swustmctown;
import org.swustmc.Utils.TabList;

import java.util.List;

import static org.swustmc.Constants.BaseConstants.*;

public class AdminCommad implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // sender = 发送命令的对象, 比如玩家/ 控制台/ 命令方块...
        // command = 命令的内容
        // label = 主命令, 不包括命令后面的参数
        // args = 命令参数数组, 不保留主命令字符串

        // 判断执行了此插件的哪个指令
        if(label.equals("swustmctown")){
            // 默认输出插件信息
            if(args.length == 0||args[0].equals("help")){
                sender.sendMessage("SWUSTMC小镇插件");
                sender.sendMessage("  插件版本: "+ PLUGIN_VERSION +", 配置版本: "+ PLUGIN.getConfig().getInt("config-version"));
                sender.sendMessage("  指令: ");
                sender.sendMessage("    - /swustmctown help - 显示该信息");
                sender.sendMessage("    - /swustmctown reload - 重载配置");
                sender.sendMessage("    - /swustmctown debug - 调试模式");
                return true;
            }

            // 重载配置
            else if(args[0].equals("reload")&&sender.hasPermission("swustmctown.admin")){
                Swustmctown.initialize();
                sender.sendMessage("SWUSTMCTown 已完成重载");
                return true;
            }

            // 调试模式
            else if(args[0].equals("debug")&&sender.hasPermission("swustmctown.admin")){
                DEBUG = !DEBUG;
                sender.sendMessage("SWUSTMCTown 调试模式: "+ DEBUG);
                return true;
            }
        }

        // 返回 false 时, 玩家将收到命令不存在的错误
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return TabList.returnList(args,args.length,sender);
    }
}
