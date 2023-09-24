//这是一个Spring MVC的拦截器配置类，用于配置拦截器的使用规则。
//
//        以下是这个配置类的详细解释：
//
//        InterceptorConfig - 这是拦截器配置类。
//        JwtInterceptor - 这是一个自定义的JwtInterceptor拦截器，用于进行JWT（JSON Web Token）的验证。
//        addInterceptors - 这是WebMvcConfigurer接口的方法，用于添加拦截器到拦截器注册表。
//        registry.addInterceptor(new JwtInterceptor()) - 将JwtInterceptor拦截器添加到拦截器注册表中。
//        .addPathPatterns(“/666”) - 设置拦截器的拦截路径为"/666"，即只有匹配该路径的请求会被拦截。
//        .excludePathPatterns(“/patient/pdf”) - 设置不需要被拦截的路径，即匹配"/patient/pdf"的请求会被排除在拦截之外。
//        .excludePathPatterns(“//login") - 设置不需要被拦截的路径，即匹配"/login"的请求会被排除在拦截之外。其中”**"表示任意多级目录。

package com.rabbiter.hospital.config;

import com.rabbiter.hospital.interceptors.JwtInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/666")
                .excludePathPatterns("/patient/pdf")
                .excludePathPatterns("/**/login");
    }
}
