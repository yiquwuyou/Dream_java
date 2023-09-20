package com.rabbiter.hospital.config;

import com.rabbiter.hospital.interceptors.JwtInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 配置 SpringBoot 应用程序中的拦截器，
// 具体来说，它定义了一个名为 InterceptorConfig 的配置类，实现了 WebMvcConfigurer 接口，并重写了 addInterceptors 方法，以配置应用程序的拦截器
@Component
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    // 这个方法用于配置拦截器。在这里，它添加了一个名为 JwtInterceptor 的拦截器，并定义了拦截器的作用路径和排除路径
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加了一个新的拦截器实例
        registry.addInterceptor(new JwtInterceptor())
                // 指定了需要拦截的请求路径。在这个例子中，只有请求路径为 "/666" 的请求会被拦截
                .addPathPatterns("/666")
                // 指定了不需要拦截的请求路径。在这个例子中，请求路径为 "/patient/pdf" 的请求不会被拦截
                .excludePathPatterns("/patient/pdf")
                // 指定了不需要拦截的请求路径。它使用通配符 "**" 匹配任意目录，所以所有以 "/login" 结尾的路径都不会被拦截
                .excludePathPatterns("/**/login");
    }
}
