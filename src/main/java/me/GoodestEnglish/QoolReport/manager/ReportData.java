package me.GoodestEnglish.QoolReport.manager;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class ReportData {

    @Getter public static Map<UUID, ReportData> data = new HashMap<>();

    private UUID uuid;
    private long lastTimeReport;
    private Map<Long, String> chatMessage;

    public ReportData(UUID uuid) {
        this.uuid = uuid;
        chatMessage = new HashMap<>();
    }

    public static ReportData getDataByUUID(UUID uuid) {
        if (!data.containsKey(uuid)) {
            ReportData reportData = new ReportData(uuid);
            data.put(uuid, reportData);
            return reportData;
        }
        return data.get(uuid);
    }

    public boolean canReport() {
        return System.currentTimeMillis() - lastTimeReport > 60000L;
    }

    public double getAllowReportTime() {
        if (System.currentTimeMillis() - lastTimeReport > 60000L) {
            return -1;
        } else {
            return ((double) lastTimeReport + 60000L - System.currentTimeMillis()) / 1000;
        }
    }

    public void addChatMessage(String msg) {
        chatMessage.put(System.currentTimeMillis(), msg);

    }

}
