package com.mycreazy.springbootweb.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Time: 上午10:54
 **/
public class DateUtil {
    /**
     * 获取昨天的日期
     * @return
     */
    public  static  String getLastDayDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);
        date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dateString = format.format(date);

        return  dateString;
    }

    /**
     * 对比时间字符串
     * @param date1Str
     * @param date2Str
     * @return
     */
    public static int compareDate(String date1Str, String date2Str) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(date1Str);
            Date dt2 = df.parse(date2Str);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
