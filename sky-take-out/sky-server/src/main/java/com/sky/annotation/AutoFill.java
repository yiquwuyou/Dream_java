package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于标识某个方法需要进行功能字段自动填充处理
 */
// @Target(ElementType.METHOD)：表示该注解可以应用到方法上。
// @Retention(RetentionPolicy.RUNTIME)：表示该注解在运行时保留，并且可以通过反射机制获取到注解信息。
// public @interface AutoFill {}：用于定义注解的名称
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    // 数据库操作类型：UPDATE INSRET
    // 在此基础上，添加了一个成员变量 value，它的类型是 OperationType。
    // OperationType 是一个枚举类型(在sky-commmon里自己定义的)，用于表示数据库操作类型，比如 UPDATE、INSERT 等
    OperationType value();
}
