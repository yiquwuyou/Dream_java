package com.example.demo.config;

import com.example.demo.common.ApplicationVariable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// HandlerInterceptor是 Spring MVC 提供的一种用于 拦截 和 处理请求 接口
// 想要实现拦截器，就要继承这个接口
// 在 Spring 应用程序中，@Component 注解用于将一个类声明为一个 Bean 组件
// 这样，Spring 在扫描组件时会识别并实例化该类，成为一个可被 Spring 管理的 Bean。
@Component
public class LoginInterceptor implements HandlerInterceptor {

    // 重写 preHandle 方法，这是接口里面提供的方法，直接 Generate 然后 Override Methods 两个快捷操作生成各种构造方法

    // @Override 用于标识一个方法覆盖（Override） 了父类中的方法或实现了接口中定义的方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断用户登录
        // 先拿到 session false的意思是有session就拿，没有就不要强求
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(ApplicationVariable.SESSION_KEY_USERINFO) != null) {
            // 用户已经登录
            return true;
        }
        // 当代码执行到此处说明用户未登录
        // 正式工作后后端仅需把错误信息发送给前端，跳转页面由前端负责
        // 这里为了简单直接就由后端跳转了
        response.sendRedirect("/login.html");
        return false;
    }
    // 拦截器至此结束 接下来去配置拦截规则
}
