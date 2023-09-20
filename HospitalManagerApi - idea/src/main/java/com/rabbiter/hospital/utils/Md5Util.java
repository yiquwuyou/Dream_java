package com.rabbiter.hospital.utils;

import org.springframework.util.DigestUtils;

//Md5Util 类是一个用于生成 MD5 哈希值的工具类。它的主要作用是将输入的字符串进行 MD5 哈希运算，并返回哈希后的结果。
//
//        这个工具类包含了以下方法：
//
//        getMD5(String str)：这个方法接受一个字符串作为参数，并将该字符串与一个盐值（salt）混合后进行 MD5 哈希运算。
//        盐值的作用是增加哈希的复杂性，提高安全性。最终，这个方法返回哈希后的 MD5 值。
//        在具体的使用场景中，你可以调用 Md5Util 的 getMD5 方法来对敏感信息（如密码）进行哈希处理，以增加安全性。通常，哈希后的散列值会存储在数据库中，而不是明文密码。
//        在用户登录时，你可以对用户提供的密码进行相同的哈希处理，然后与数据库中存储的散列值进行比较来验证用户的身份。这有助于保护用户密码的安全性
public class Md5Util {

    // 盐，用于混交md5
    // salt 是一个随机的字符串，通常被添加到密码之前，然后一起进行哈希处理。它的作用是增加哈希的复杂性，提高安全性，以防止彩虹表等攻击
    // 实际使用时，会使用随机的盐值
    private static String salt = "asdwqAsd12_qS";

    public static String getMD5(String str) {
        String base = str + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


}