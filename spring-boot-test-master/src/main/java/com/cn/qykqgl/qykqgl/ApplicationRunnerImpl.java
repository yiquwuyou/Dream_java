package com.cn.qykqgl.qykqgl;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**启动成功后控制台出现提示*/
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("" +
                "         ===///    , ,-. ,-   .-, , , ,-  ,_  ,  ,_ ,-   \n" +
                "         --///    /_ |_/ _)  /\"\"|/|/ |_] /=_ /_ /=_ _)   \n" +
                "       ___///    __    __  __  ______  ____    ___       \n" +
                "         ///  --/||  --// .'   //  \"/ _//  )) (|  \"/     \n" +
                "     ---///   /' ||  _//.;  __//_.    //_=\"'  _\\\\        \n" +
                "     --///- -/---||  //'||   //  ', _//  ;|     \\\\     ; \n" +
                "______/// __/_  _||_/<  `||_//__\"/ _// --|\\ /-._//    /  \n" +
                "    _///      ,                        ---\\\\\\       .'   \n" +
                " ___///_____\"/                                           \n" +
                "                        启 动 成 功                        \n" +
                "               Los Angeles Lakers by Jadeon    \n" +
                "  原创git地址:https://gitee.com/UniqueIT/spring-boot-test    \n"
                );


    }
}
