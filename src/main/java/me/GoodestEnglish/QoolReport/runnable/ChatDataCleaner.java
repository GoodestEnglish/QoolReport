package me.GoodestEnglish.QoolReport.runnable;

import me.GoodestEnglish.QoolReport.QoolReport;
import me.GoodestEnglish.QoolReport.manager.ReportData;
import me.GoodestEnglish.QoolReport.util.Log;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatDataCleaner {

    public ChatDataCleaner() {
        start();
    }

    private void start() {
        new BukkitRunnable() {
            //每一分鐘刪除一次紀錄, 因為係使用Async 所以應該對伺服器唔會有太大影響
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                for (ReportData data : ReportData.getData().values()) {
                    data.getChatMessage().keySet().removeIf(time -> System.currentTimeMillis() - time > 1000 * 60 * 5);
                }
                long finishTime = System.currentTimeMillis();

                Log.show(Log.LogLevel.LOW, "[QoolReport] 成功清除超過五分鐘的聊天室說話紀錄 (耗費 " + (finishTime - startTime) + "ms)");
            }
        }.runTaskTimerAsynchronously(QoolReport.get(), 20L * 60, 20L * 60);
    }

}
