package com.rabbiter.hospital.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//TodayUtil 工具类包含了几个日期操作的方法：
//
//        getToday() 方法用于获取当前日期和时间，并以 "yyyy-MM-dd HH:mm:ss" 的格式返回。
//
//        getTodayYmd() 方法用于获取当前日期，并以 "yyyy-MM-dd" 的格式返回。
//
//        getPastDate(int past) 方法用于获取过去第 past 天的日期，past 是一个整数参数，表示要获取过去多少天的日期。它返回一个以 "yyyy-MM-dd" 的格式表示的日期字符串。
//
//        这些方法可以用于获取当前日期和时间、当前日期，以及过去某一天的日期
public class TodayUtil {

    public static String getToday(){
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String date = String.valueOf(calendar.get(Calendar.DATE));
        String day = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));
        if(calendar.get(Calendar.MONTH)+1 < 10)
            month = "0"+month;
        if(calendar.get(Calendar.DATE) < 10)
            date = "0"+date;
        return year+"-"+month+"-"+date+" "+day+":"+minute+":"+second;
    }
    public static String getTodayYmd(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }
    //获取过去的第past天
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        //System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        //System.out.println(today);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }
}
