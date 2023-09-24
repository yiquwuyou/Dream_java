//这段代码是一个RandomUtil工具类，包含了两个静态方法。
//
//        方法randomOid用于在给定的oId基础上生成一个随机的整数ID，生成规则是在基础oId上加上一个随机数flag，flag的取值范围是0到9999之间。如果flag小于1000，则将其加上1000。
//        该方法返回生成的随机ID。
//
//        方法randomCode用于生成一个随机的整数验证码，生成规则是生成一个0到999999之间的随机数flag。如果flag小于100000，则将其加上100000。
//        该方法返回生成的随机验证码。
//
//        这些方法可以用于生成随机的订单ID和验证码等场景。

package com.rabbiter.hospital.utils;

import java.util.Random;

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
