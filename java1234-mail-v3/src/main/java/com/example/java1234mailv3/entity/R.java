package com.example.java1234mailv3.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面响应entity
 * @author java1234_小锋
 * @site www.java1234.com
 * @company Java知识分享网
 * @create 2019-08-13 上午 10:00
 */
//这段代码定义了一个名为 R 的类，它继承了 HashMap<String, Object> 类，用于表示页面响应的实体。主要功能如下：
//
//        构造函数 public R()：初始化一个 R 对象，并设置默认的 code 值为 0。
//
//public static R error()：创建一个表示错误的 R 对象，code 默认为 500，msg 默认为 "未知异常，请联系管理员"。
//
//public static R error(String msg)：创建一个表示错误的 R 对象，code 默认为 500，msg 为传入的错误消息。
//
//public static R error(int code, String msg)：创建一个表示错误的 R 对象，可以指定错误码和错误消息。
//
//public static R ok(String msg)：创建一个表示成功的 R 对象，同时设置返回消息。
//
//public static R ok(Map<String, Object> map)：创建一个表示成功的 R 对象，同时可以传入一个键值对集合。
//
//public static R ok()：创建一个表示成功的 R 对象，不带任何附加信息。
//
//public R put(String key, Object value)：向 R 对象中添加键值对，并返回 R 对象自身，用于链式调用。
//
//        这个类的主要目的是封装接口的响应信息，方便统一格式的返回结果，通常用于 RESTful 风格的接口中。开发人员可以根据需要使用这个类来构建接口响应
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
