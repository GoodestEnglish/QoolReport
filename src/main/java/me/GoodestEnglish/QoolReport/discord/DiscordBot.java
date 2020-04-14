package me.GoodestEnglish.QoolReport.discord;

import lombok.Getter;
import me.GoodestEnglish.QoolDiscordCore.QoolDiscordCoreAPI;
import me.GoodestEnglish.QoolReport.QoolReport;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import java.util.Iterator;
import java.util.List;

@Getter
public class DiscordBot {

    private QoolReport plugin = QoolReport.get();
    private String reportChannelID;

    public DiscordBot() {
        reportChannelID = plugin.getConfigFile().getString("discord.channel-id");
    }

    public void sendReportMessage(final String message) {
        QoolDiscordCoreAPI.getJDA().getTextChannelById(reportChannelID).sendMessage(new EmbedBuilder()
                .setDescription(message).build())
                .queue();
    }

}
