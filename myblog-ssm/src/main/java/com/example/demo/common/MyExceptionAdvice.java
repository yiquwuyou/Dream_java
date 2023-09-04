package com.example.demo.common;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 统一异常处理和返回

// @RestControllerAdvice 是 @ControllerAdvice 和 @ResponseBody 的结合
// @ControllerAdvice 用于对全局控制器进行增强和异常处理。通过标记一个类，可以实现对所有控制器的统一配置和处理
// @ResponseBody 用于将方法的返回值直接作为响应的内容返回给客户端，而不是直接返回一个页面
@RestControllerAdvice
public class MyExceptionAdvice {

    // 此注解修饰方法，用来处理控制器中抛出的特定类型的异常
    @ExceptionHandler(Exception.class)
    public AjaxResult doException(Exception e) {
        return AjaxResult.fail(-1, e.getMessage());
    }

}
