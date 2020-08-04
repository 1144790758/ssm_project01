package com.lhl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @athor:lhl
 * @create:2020-01-31 20:35
 */
public class DateUtil {

    /**
     * 日期转字符串
     * @return
     */
    public static String DateToString(Date date,String pattem){
        SimpleDateFormat sdf=new SimpleDateFormat(pattem);
        String str = sdf.format(date);
        return str;
    }

    public static Date StringToDate(String dateStr,String pattem){
        SimpleDateFormat sdf=new SimpleDateFormat(pattem);
        Date date=null;
        try {
             date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
