//这段代码是一个TodayUtil工具类，包含了三个静态方法用于获取日期相关的信息。
//
//        方法getToday用于获取当前日期和时间的字符串表示。它通过Calendar.getInstance()获取当前的Calendar实例，然后使用Calendar的get方法获取年、月、日、时、分、秒的值，并转化为对应的字符串。如果月份或日期小于10，会在前面加上0以保持两位数的格式。最后将这些值拼接为一个字符串返回。
//
//        方法getTodayYmd用于获取当前日期的字符串表示，格式为"yyyy-MM-dd"。它首先获取当前的Calendar实例，然后使用Calendar的getTime方法获取对应的Date对象。接下来使用SimpleDateFormat将Date对象格式化为指定的日期字符串，最后返回该字符串。
//
//        方法getPastDate用于获取过去第past天的日期的字符串表示，格式为"yyyy-MM-dd"。它首先获取当前的Calendar实例，并使用Calendar的set方法将"DAY_OF_YEAR"字段减去指定的past值，相当于获取过去第past天的日期。然后使用Calendar的getTime方法获取对应的Date对象，再通过SimpleDateFormat将Date对象格式化为指定的日期字符串，最后返回该字符串。
//
//        这些方法可以方便地获取当前日期、当前日期的年月日时分秒表示，以及过去的某一天的日期。

package com.rabbiter.hospital.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
