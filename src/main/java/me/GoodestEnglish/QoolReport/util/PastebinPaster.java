package me.GoodestEnglish.QoolReport.util;

import me.GoodestEnglish.QoolReport.QoolReport;
import me.nrubin29.pastebinapi.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class PastebinPaster {

    public static void paste(Player player, String target, String string) {
        Log.show(Log.LogLevel.MEDIUM, "Your pastebin key is " + QoolReport.get().getConfigFile().getString("pastebin.api-key") + ", trying to paste now...");
        PastebinAPI api = new PastebinAPI(QoolReport.get().getConfigFile().getString("pastebin.api-key"));

        User user = null;
        try {
            user = api.getUser(QoolReport.get().getConfigFile().getString("pastebin.username"), QoolReport.get().getConfigFile().getString("pastebin.password"));
        } catch (PastebinException e) {
            e.printStackTrace();
        }

        if (user == null) {
            player.sendMessage(CC.RED + "在把資料傳送到PasteBin 的時候發生了錯誤, 請於系統管理員聯絡修復這個錯誤 (1)");
            return;
        }

        CreatePaste paste = user.createPaste()
                .withName(target + "'s chat log")
                .withFormat(Format.None)
                .withPrivacyLevel(PrivacyLevel.PRIVATE)
                .withExpireDate(ExpireDate.ONE_DAY)
                .withText(string);

        String url = null;
        try {
            url = paste.post();
        } catch (PastebinException | IOException e) {
            e.printStackTrace();
        }

        if (url == null) {
            player.sendMessage(CC.RED + "在把資料傳送到PasteBin 的時候發生了錯誤, 請於系統管理員聯絡修復這個錯誤 (2)");
            return;
        }

        player.sendMessage(CC.translate("&a這個是 &e" + target + " &a在五分鐘內的聊天室紀錄 &7(不包含私人信息): &6&n" + url));
    }

    public static void paste(CommandSender player, String target, String string) {
        Log.show(Log.LogLevel.MEDIUM, "Your pastebin key is " + QoolReport.get().getConfigFile().getString("pastebin.api-key") + ", trying to paste now...");
        PastebinAPI api = new PastebinAPI(QoolReport.get().getConfigFile().getString("pastebin.api-key"));

        User user = null;
        try {
            user = api.getUser(QoolReport.get().getConfigFile().getString("pastebin.username"), QoolReport.get().getConfigFile().getString("pastebin.password"));
        } catch (PastebinException e) {
            e.printStackTrace();
        }

        if (user == null) {
            player.sendMessage(CC.RED + "在把資料傳送到PasteBin 的時候發生了錯誤, 請於系統管理員聯絡修復這個錯誤 (1)");
            return;
        }

        if (string.isEmpty()) {
            player.sendMessage(CC.RED + "沒有任何資料可以傳送!");
            return;
        }

        CreatePaste paste = user.createPaste()
                .withName(target + "'s chat log")
                .withFormat(Format.None)
                .withPrivacyLevel(PrivacyLevel.PRIVATE)
                .withExpireDate(ExpireDate.ONE_DAY)
                .withText(string);

        String url = null;
        try {
            url = paste.post();
        } catch (PastebinException | IOException e) {
            e.printStackTrace();
        }

        if (url == null) {
            player.sendMessage(CC.RED + "在把資料傳送到PasteBin 的時候發生了錯誤, 請於系統管理員聯絡修復這個錯誤 (2)");
            return;
        }

        player.sendMessage(CC.translate("&a這個是 &e" + target + " &a在五分鐘內的聊天室紀錄 &7(不包含私人信息): &6&n" + url));
    }

}
