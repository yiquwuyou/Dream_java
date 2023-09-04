package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// 使用 @Configuration 将此类托管给 Spring
@Configuration
public class MyConfig implements WebMvcConfigurer {

    // 因为已将将它托管给Spring了，所以这里直接用就行
    // 该注解用于实现依赖注入
    @Autowired
    private LoginInterceptor loginInterceptor;
    // 重写此方法后才能把拦截器注入到当前的配置文件里面
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")  // 拦截所有 url 地址
                .excludePathPatterns("/login.html")        // 针对以下四个界面不做拦截操作
                .excludePathPatterns("/reg.html")
                .excludePathPatterns("/blog_list.html")
                .excludePathPatterns("/blog_content.html")
                .excludePathPatterns("/css/**")          // css底下的所有资源全不拦
                .excludePathPatterns("/editor.md/**")    // markdown 编译器底下所有配置也是不拦的
                .excludePathPatterns("/img/**")          // 图片不拦
                .excludePathPatterns("/js/**")           // js 当然也不能拦
                .excludePathPatterns("/user/reg")        // 后端代码中的 user url 下的 reg url 是不拦的
                .excludePathPatterns("/user/login")     // 后端代码中的 user url 下的 login url 是不拦的
                .excludePathPatterns("/user/islogin")
                .excludePathPatterns("/art/addrcount")
                .excludePathPatterns("/art/getlistbypage")
                .excludePathPatterns("/art/getcount")
                .excludePathPatterns("/art/getdetail");
        // 后续可能还有，再补充就行
    }
}
