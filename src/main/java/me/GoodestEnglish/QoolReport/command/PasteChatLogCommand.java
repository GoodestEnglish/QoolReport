package me.GoodestEnglish.QoolReport.command;

import me.GoodestEnglish.QoolReport.QoolReport;
import me.GoodestEnglish.QoolReport.manager.ReportData;
import me.GoodestEnglish.QoolReport.util.CC;
import me.GoodestEnglish.QoolReport.util.PastebinPaster;
import me.GoodestEnglish.QoolReport.util.Permission;
import me.nrubin29.pastebinapi.*;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class PasteChatLogCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission(Permission.STAFF_PERMISSION)) {
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(CC.RED + "指令用法: /getchatlogs <玩家名稱>");
            return false;
        }

        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        if (!ReportData.getData().containsKey(player.getUniqueId())) {
            sender.sendMessage(CC.RED + "該玩家沒有任何聊天室紀錄!");
            return false;
        }

        PastebinPaster.paste(sender, args[0], QoolReport.get().getReportManager().formatPastebinMessage(ReportData.getDataByUUID(player.getUniqueId())));

        return false;
    }
}
