package me.GoodestEnglish.QoolReport.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String date = formatter.format(new Date());
        return date;
    }

}
