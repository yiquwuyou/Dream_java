package com.rabbiter.hospital.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 这段代码配置了 MyBatis Plus 的两个核心插件：分页插件（PaginationInterceptor）和乐观锁插件（OptimisticLockerInterceptor）
@Configuration
public class MyBatisPlusConfig {
    /**
     * 配置分页插件
     */
    // 分页插件用于在查询数据库时支持分页功能。它可以自动解析并拦截 Page 对象，实现分页查询。
    // 在 MyBatis Plus 中，你可以直接使用 Page 对象来进行分页查询，而无需手动编写分页查询的 SQL 语句
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
    /**
     * 配置乐观锁插件
     */
    // 乐观锁插件用于处理乐观锁机制，它通常用于避免多个用户同时修改同一数据时的冲突问题。
    // 在 MyBatis Plus 中，你可以使用 @Version 注解来标识一个字段，表示这是一个乐观锁字段。当更新数据时，插件会检查该字段的值是否匹配，如果不匹配则会抛出乐观锁异常
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }


}
