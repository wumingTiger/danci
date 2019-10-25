package com.example.wero.a1_20154816395.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-21
 */

public class TimeUtils {
    public static long ONE_DAY = 1000*60*60*24;;
    private static SimpleDateFormat df;

    public static String getShortDate(Date date, Long l){
        date.setTime(date.getTime() - l);
        df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
    public static String getShortDate(){
        Date date = new Date();
        df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
}
