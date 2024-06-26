package com.example.questionnaire.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;
import java.util.List;

// 配置 SpringBoot 应用程序中的拦截器，
// 具体来说，它定义了一个名为 InterceptorConfig 的配置类，实现了 WebMvcConfigurer 接口，并重写了 addInterceptors 方法，以配置应用程序的拦截器
@Component
@Slf4j
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始设置静态资源映射...");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/login.html").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/*.html").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }

    private static List<String> excludePath = Arrays.asList(
            "/css/**",
            "/js/**",
            "/pic/**",
            "/**/*.html",
            "/test/**");

    // todo：下面这个是用来调试开放static的，上面那个才是之前的
//    private static List<String> excludePath = Arrays.asList(
//            "/css/**",
//            "/js/**",
//            "/pic/**",
//            "/**/*.html",
//            "/test/**");
    @Override
    // 这个方法用于配置拦截器。在这里，它添加了一个名为 JwtInterceptor 的拦截器，并定义了拦截器的作用路径和排除路径
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");

        // 添加了一个新的拦截器实例
        registry.addInterceptor(jwtInterceptor)
                // 指定了需要拦截的请求路径
                .addPathPatterns("/**")
                // 指定了不需要拦截的请求路径。在这个例子中，请求路径为 "/patient/pdf" 的请求不会被拦截
                // 指定了不需要拦截的请求路径。它使用通配符 "**" 匹配任意目录，所以所有以 "/login" 结尾的路径都不会被拦截
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/ceshi")
                .excludePathPatterns(excludePath)
                .excludePathPatterns("/WebSocketMessage")
                .excludePathPatterns("/file/download");
    }

}
