package me.GoodestEnglish.QoolReport.listener;

import me.GoodestEnglish.QoolReport.manager.ReportData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        ReportData.getDataByUUID(player.getUniqueId()).addChatMessage(event.getMessage());
    }

}
