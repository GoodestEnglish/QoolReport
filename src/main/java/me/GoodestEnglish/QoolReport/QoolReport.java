package me.GoodestEnglish.QoolReport;

import lombok.Getter;
import lombok.Setter;
import me.GoodestEnglish.QoolReport.command.PasteChatLogCommand;
import me.GoodestEnglish.QoolReport.command.ReportCommand;
import me.GoodestEnglish.QoolReport.discord.DiscordBot;
import me.GoodestEnglish.QoolReport.file.BasicConfigFile;
import me.GoodestEnglish.QoolReport.listener.ChatListener;
import me.GoodestEnglish.QoolReport.manager.ReportManager;
import me.GoodestEnglish.QoolReport.runnable.ChatDataCleaner;
import me.GoodestEnglish.QoolReport.util.menu.ButtonListener;
import me.GoodestEnglish.QoolReport.util.menu.task.MenuUpdateTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class QoolReport extends JavaPlugin {

    public static QoolReport instance;

    @Getter private BasicConfigFile configFile;

    @Getter private DiscordBot discordBot;
    @Getter private ReportManager reportManager;

    public void onEnable() {
        instance = this;

        configFile = new BasicConfigFile(this, "config.yml");

        discordBot = new DiscordBot();
        reportManager = new ReportManager();

        getCommand("report").setExecutor(new ReportCommand());
        getCommand("getchatlogs").setExecutor(new PasteChatLogCommand());

        getServer().getPluginManager().registerEvents(new ButtonListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);

        new ChatDataCleaner();
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MenuUpdateTask(), 20L, 20L);
    }

    public static QoolReport get() {
        return instance;
    }
}
