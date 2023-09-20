package com.rabbiter.hospital.utils;

import java.util.Random;

//这个RandomUtil工具类中包含两个方法用于生成随机数：
//
//        randomOid(int oId) 方法：生成一个随机的订单号，它接受一个整数参数 oId，然后生成一个介于1000和9999之间的随机数，并将其与传入的 oId 相加，以确保生成的订单号是唯一的。
//
//        randomCode() 方法：生成一个六位的随机数，它生成一个介于100000和999999之间的随机数，确保生成的随机数是六位数。
//
//        这些方法可以用于生成随机的订单号和验证码等
public class RandomUtil {
    public static Integer randomOid(int oId){
        int flag = new Random().nextInt(9999);
        if (flag < 1000)
            flag += 1000;
        return oId+flag;
    }
    public static Integer randomCode(){
        int flag = new Random().nextInt(999999);
        if (flag < 100000)
            flag += 100000;
        return flag;
    }

}
