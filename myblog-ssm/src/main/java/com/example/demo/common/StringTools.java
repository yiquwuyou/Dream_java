package com.example.demo.common;


import org.springframework.util.StringUtils;

// 截取字符给前端展示用
public class StringTools {
    public static String subLength(String val, int maxLength) {
        if (!StringUtils.hasLength(val) || maxLength <= 0)
            return val;
        if (val.length() <= maxLength)
            return val;
        return val.substring(0, maxLength);
    }
}
