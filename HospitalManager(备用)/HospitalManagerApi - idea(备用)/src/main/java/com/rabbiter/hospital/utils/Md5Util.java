//这是一个 Md5Util 类的代码片段，用于对给定的字符串进行MD5加密。它具有以下方法：
//
//        getMD5(String str): 生成给定字符串的MD5哈希值。
//        在生成MD5哈希值时，它使用了一个固定的盐值（“asdwqAsd12_qS”）来增加安全性。字符串首先与盐值相连接，然后通过DigestUtils类的md5DigestAsHex方法计算MD5哈希值，并将结果以十六进制字符串形式返回。
//
//        需要注意的是，该类使用了Spring框架的DigestUtils工具类来进行MD5加密。

package com.rabbiter.hospital.utils;

import org.springframework.util.DigestUtils;

public class Md5Util {

    //盐，用于混交md5
    private static String salt = "asdwqAsd12_qS";

    public static String getMD5(String str) {
        String base = str + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


}