package com.example.questionnaire;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ceshi {
    public static void main1(String[] args) {
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 使用格式化输出当前日期和时间，带上年月日时分秒关键词
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        System.out.println("当前日期和时间: " + formattedDateTime);
    }

    public static void main(String[] args) {
        UUID longuuid = UUID.randomUUID();
        String uuid = longuuid.toString().substring(0, 8);
        System.out.println("生成的短UUID: " + uuid);
    }
}
