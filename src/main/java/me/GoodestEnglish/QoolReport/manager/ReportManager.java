package me.GoodestEnglish.QoolReport.manager;

import me.GoodestEnglish.QoolReport.QoolReport;
import me.GoodestEnglish.QoolReport.enums.ReportType;
import me.GoodestEnglish.QoolReport.util.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ReportManager {

    public Map<UUID, Integer> gotReportedTimes = new HashMap<>();

    public void sendReport(Player player, Player target, ReportType reportType) {
        player.sendMessage(CC.translate("&a謝謝你的舉報! :D"));

        ReportData.getDataByUUID(player.getUniqueId()).setLastTimeReport(System.currentTimeMillis());
        addGotReportedTimes(target.getUniqueId());

        ReportUtil.broadcastReport(player, target, reportType);

        QoolReport.get().getDiscordBot().sendReportMessage(
                player.getName() + " 舉報 " + target.getName() + " 使用 " + reportType.getReadable() + "\n\n" +
                "被舉報者被舉報次數 (只計算本次伺服器運行期間): " + getGotReportedTimes(target.getUniqueId()) + "\n" +
                "被舉報者網絡延遲: " + target.spigot().getPing() + "\n" +
                "舉報者網絡延遲: " + player.spigot().getPing() + "\n" +
                "TPS: " + Bukkit.spigot().getTPS()[0] + "\n" +
                "日期: " + DateUtil.getCurrentDate()
        );

        if (reportType == ReportType.CHAT) {
            if (ReportData.getDataByUUID(target.getUniqueId()).getChatMessage().isEmpty()) {
                QoolReport.get().getDiscordBot().sendReportMessage(target.getName() + " 沒有任何聊天室紀錄!");
                return;
            }
            String string = target.getName() + " 的聊天室紀錄: " + "\n\n" + formatDiscordMessage(ReportData.getDataByUUID(target.getUniqueId()));
            QoolReport.get().getDiscordBot().sendReportMessage(string);
        }
    }

    public int getGotReportedTimes(UUID uuid) {
        if (!gotReportedTimes.containsKey(uuid)) {
            gotReportedTimes.put(uuid, 0);
        }
        return gotReportedTimes.get(uuid);
    }

    public void addGotReportedTimes(UUID uuid) {
        if (!gotReportedTimes.containsKey(uuid)) {
            gotReportedTimes.put(uuid, 0);
        }
        gotReportedTimes.replace(uuid, gotReportedTimes.get(uuid) + 1);
    }

    public String formatPastebinMessage(ReportData data) {
        StringBuilder value = new StringBuilder();

        value.append("**Pastebin only supports English character, if there's other character it will throw error \n");

        for (Map.Entry<Long, String> map : data.getChatMessage().entrySet()) {
            value.append("(").append(Math.round(((float) (System.currentTimeMillis() - map.getKey()) / 1000))).append(" seconds ago) ").append(Bukkit.getOfflinePlayer(data.getUuid()).getName()).append(": ").append(map.getValue()).append("\n");
        }

        return value.toString();
    }

    public String formatDiscordMessage(ReportData data) {
        StringBuilder value = new StringBuilder();

        for (Map.Entry<Long, String> map : data.getChatMessage().entrySet()) {
            value.append("(").append(Math.round(((float) (System.currentTimeMillis() - map.getKey()) / 1000))).append(" 秒前) ").append(Bukkit.getOfflinePlayer(data.getUuid()).getName()).append(": ").append(map.getValue()).append("\n");
        }

        return value.toString();
    }

}
