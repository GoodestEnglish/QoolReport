package me.GoodestEnglish.QoolReport.listener;

import litebans.api.Database;
import me.GoodestEnglish.QoolReport.manager.ReportData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (event.isCancelled()) {
            return;
        }
        if (Database.get().isPlayerMuted(player.getUniqueId(), player.getAddress().getAddress().toString())) {
            return;
        }
        ReportData.getDataByUUID(player.getUniqueId()).addChatMessage(event.getMessage());

    }

}
