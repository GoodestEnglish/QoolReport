package me.GoodestEnglish.QoolReport.command;

import me.GoodestEnglish.QoolReport.manager.ReportData;
import me.GoodestEnglish.QoolReport.menu.ReportMenu;
import me.GoodestEnglish.QoolReport.util.CC;
import me.GoodestEnglish.QoolReport.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(CC.RED + "指令用法: /report <玩家名稱>");
            return false;
        }

        if (player.getName().equalsIgnoreCase(args[0])) {
            player.sendMessage(CC.RED + "你不能舉報自己!");
            return false;
        }

        if (!ReportData.getDataByUUID(player.getUniqueId()).canReport()) {
            player.sendMessage(CC.RED + "你還需要 " + ReportData.getDataByUUID(player.getUniqueId()).getAllowReportTime() + " 秒才能舉報其他人");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(CC.RED + "玩家不存在!");
            return false;
        }

        player.sendMessage(CC.RED + "*請注意: 請不要胡亂舉報他人, 胡亂舉報他人將會受到懲罰");
        new ReportMenu(target).openMenu(player);

        return false;
    }
}
