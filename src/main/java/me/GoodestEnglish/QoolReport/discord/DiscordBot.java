package me.GoodestEnglish.QoolReport.discord;

import lombok.Getter;
import me.GoodestEnglish.QoolReport.QoolReport;
import me.GoodestEnglish.QoolReport.event.LoadJDAListenerEvent;
import me.GoodestEnglish.QoolReport.util.Log;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@Getter
public class DiscordBot {

    private QoolReport plugin = QoolReport.get();
    private JDA jda;
    private String reportChannelID;

    public DiscordBot() {
        try {
            jda = JDABuilder.createDefault(plugin.getConfigFile().getString("discord.token-bot")).build();
            reportChannelID = plugin.getConfigFile().getString("discord.channel-id");
            LoadJDAListenerEvent event = new LoadJDAListenerEvent();
            event.call();

            event.getListenerAdapters().forEach(jda::addEventListener);

            Arrays.asList(
                    new ListenerAdapter() {
                        @Override
                        public void onReady(@NotNull final ReadyEvent event) {
                            Log.show(Log.LogLevel.LOW, "[QoolDiscordCore] Discord 機械人已成功加載!");
                            super.onReady(event);
                        }
                    }
            ).forEach(jda::addEventListener);

        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public void sendReportMessage(final String message) {
        jda.getTextChannelById(reportChannelID).sendMessage(new EmbedBuilder()
                .setDescription(message).build())
                .queue();
    }

}
