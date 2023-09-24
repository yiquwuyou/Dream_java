//这是一个MyBatis Plus的配置类，用于配置MyBatis Plus的插件。
//
//        以下是这个配置类的详细解释：
//
//        MyBatisPlusConfig - 这是MyBatis Plus的配置类。
//@Bean - 使用@Bean注解声明了两个Bean，分别是PaginationInterceptor和OptimisticLockerInterceptor。
//        paginationInterceptor() - 这是方法体，用于创建并返回一个PaginationInterceptor实例，用于配置分页插件。
//        optimisticLockerInterceptor - 这是方法体，用于创建并返回一个OptimisticLockerInterceptor实例，用于配置乐观锁插件。
//
//package com.rabbiter.hospital.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {
    /**
     * 配置分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
    /**
     * 配置乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }


}
