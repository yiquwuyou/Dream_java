package com.example.java1234mailv3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// web 项目配置类
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
//    这段代码是一个 Spring Boot Web 项目的配置类，它实现了 WebMvcConfigurer 接口，用于配置 Spring Boot 的 MVC（Model-View-Controller）特性。具体来说：
//
//    @Configuration 注解标识这是一个配置类。
//
//    WebMvcConfigurer 接口提供了一种扩展 Spring Boot MVC 配置的方式，你可以通过实现这个接口来自定义一些 MVC 的行为。
//
//    addResourceHandlers(ResourceHandlerRegistry registry) 方法是 WebMvcConfigurer 接口中的一个方法，
//    用于添加自定义的静态资源处理器。在这个方法内部，你配置了一个资源处理器，它处理 /image/swiper/** 路径下的静态资源。

// registry.addResourceHandler("/image/swiper/**") 定义了需要处理的资源路径模式，
// 这里表示所有以 /image/swiper/ 开头的请求都会被这个资源处理器处理。
//
// addResourceLocations("file:D:\\code_github\\微信小程序商城完整源码\\微信小程序商城初步完整源码\\图片
// \\java1234-mall\\swiperImgs\\") 指定了静态资源的存储位置，这里是本地文件系统的路径。
//
// 通过这个配置，当客户端请求 /image/swiper/xxx 路径下的资源时，Spring Boot 会根据配置，
// 将对应的资源文件从指定的本地路径加载并返回给客户端，从而实现了静态资源的访问。
//   比如：localhost:8080/image/swiper/1.jpg
//   1.jpg 是对应    /image/swiper/  路径下的文件
// 这通常用于将图片、CSS、JavaScript 等静态文件暴露给客户端
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/swiper/**").addResourceLocations("file:D:\\code_github\\微信小程序商城完整源码\\微信小程序商城初步完整源码\\图片\\java1234-mall\\swiperImgs\\");
    }
}
