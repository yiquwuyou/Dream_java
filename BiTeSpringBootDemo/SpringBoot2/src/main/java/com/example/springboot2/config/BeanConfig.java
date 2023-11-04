package com.example.springboot2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfig {

    @Bean
    public String name2(){
        return "zhangsan";
    }

    @Bean
    public String name(){
        return "wangwu";
    }

    @Primary  // 标注默认的对象
    @Bean
    public UserInfo userInfo1(String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setName(name);
        userInfo.setAge(12);
        System.out.println("6666666666666666");
        return userInfo;
    }

    @Bean
    public UserInfo userInfo2(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(2);
        userInfo.setName("lisi");
        userInfo.setAge(13);
//        System.out.println("77777777777777777777");
        return userInfo;
    }
}
