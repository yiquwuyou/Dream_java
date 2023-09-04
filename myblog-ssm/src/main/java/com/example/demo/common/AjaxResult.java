package com.example.demo.common;


import lombok.Data;

import java.io.Serializable;

// 统一返回对象
// AjaxResult 类是一个数据传输对象（DTO），用于在前端和后端之间传递数据。
// 此处，AjaxResult 类还实现了 Serializable 接口，表示该类的对象可以进行序列化操作
// Data 注解可以生成一些常用的代码，是 Lombok 提供的注解之一
// 添加Data后，lombok 会自动生成 Getter，Setter, equals()和hashCode(), toString() 方法
// Lombok 是一个java库，通过注解的方式来减少java类中的样板代码，提高开发效率 常用注解有 @Getter @ToString @Data(集合了Getter等注解) 等
@Data
public class AjaxResult implements Serializable {
    // 把这三个信息返回给前端
    private int code;   // 表示响应状态码，一般用于表示请求的处理状态 比如200表示成功，400表示请求参数错误
    private String msg;  // 表示响应信息，一般用于携带处理结果的文字描述信息 例如，可以用于说明请求成功或失败的原因
    private Object data;  // 表示主要的数据内容，可以存储任何类型的对象 根据业务需求，可以存储返回的数据结果、错误信息、返回的对象等

    // 下面全部是重写构造方法，以满足不同参数的需要

    public static AjaxResult success(Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(200);
        ajaxResult.setMsg("");
        ajaxResult.setData(data);
        return ajaxResult;
    }

    public static AjaxResult success(Object data, String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(200);
        ajaxResult.setMsg(msg);
        ajaxResult.setData(data);
        return ajaxResult;
    }

    // 上面两个返回成功，下面两个返回失败

    // 大部分失败时都传的这个 都失败了，还要啥数据啊
    public static AjaxResult fail(Integer code, String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMsg(msg);
        ajaxResult.setData("");
        return ajaxResult;
    }

    // 但是确实有传data的场景，虽然少,不过满足你
    public static AjaxResult fail(Integer code, String msg, Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMsg(msg);
        ajaxResult.setData(data);
        return ajaxResult;
    }
}
