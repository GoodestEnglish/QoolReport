package me.GoodestEnglish.QoolReport.util;

import me.GoodestEnglish.QoolReport.QoolReport;
import me.GoodestEnglish.QoolReport.enums.ReportType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReportUtil {

    public static void broadcastReport(String s) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.hasPermission(Permission.STAFF_PERMISSION)) {
                player.sendMessage(CC.translate("&7[&6REPORT&7] &c" + s));
            }
        });
    }

    public static void broadcastReport(Player player, Player target, ReportType reportType) {
        Bukkit.getOnlinePlayers().forEach(loopPlayer -> {
            if (loopPlayer.hasPermission(Permission.STAFF_PERMISSION)) {
                loopPlayer.sendMessage(new ChatComponentBuilder("")
                        .parse(CC.translate("&7[&6REPORT&7] &a" + player.getName() + " &e舉報 &c" + target.getName() + " &e使用 &c" + reportType.getReadable() +
                                " &7(" + QoolReport.get().getReportManager().getGotReportedTimes(target.getUniqueId()) + ")"))
                        .attachToEachPart(ChatHelper.hover(
                                "&7被舉報者被舉報次數 (只計算本次伺服器運行期間): " + QoolReport.get().getReportManager().getGotReportedTimes(target.getUniqueId()) + "\n" +
                                "&7被舉報者網絡延遲: " + target.spigot().getPing() + "\n" +
                                "&7舉報者網絡延遲: " + player.spigot().getPing() + "\n" +
                                "&7TPS: " + Bukkit.spigot().getTPS()[0] + "\n" +
                                "&7日期: " + DateUtil.getCurrentDate()
                        )).create());
            }
        });
    }

}
