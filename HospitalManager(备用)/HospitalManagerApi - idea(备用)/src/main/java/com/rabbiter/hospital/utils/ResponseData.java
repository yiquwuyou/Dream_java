//这段代码是一个ResponseData类，用于封装接口响应数据。
//
//        该类包含以下属性：
//
//        status: 表示当前响应的状态，是成功还是失败的标志。
//        msg: 表示响应之后给前端的提示信息。
//        data: 表示响应成功之后返回给前端的数据。
//        该类还提供了构造方法、getter和setter方法以及toString方法，用于设置和获取属性值，并可以以字符串形式打印对象信息。
//
//        另外，该类还包含了一些静态方法：
//
//        success(String msg, Object data): 创建一个成功的响应对象，并设置响应的提示信息和数据。
//        fail(String msg): 创建一个失败的响应对象，并设置响应的提示信息。
//        success(String msg): 创建一个成功的响应对象，并设置响应的提示信息，但没有数据返回。
//        通过使用ResponseData类，可以方便地封装接口的响应数据，并进行统一的格式化输出。

package com.rabbiter.hospital.utils;


import java.io.Serializable;

public class ResponseData implements Serializable {
    /**
     * 表示当前相应的状态是成功或者失败
     */
    private int status;
    /**
     * 表示当响应之后给前端的提示信息
     */
    private String msg;
    /**
     * 表示当响应成功之后返回给前端的数据
     */
    private Object data;

    public ResponseData() {
    }

    public ResponseData(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static ResponseData success(String msg, Object data) {
        return new ResponseData(200, msg, data);
    }

    public static ResponseData fail(String msg) {
        return new ResponseData(400, msg, null);
    }

    public static ResponseData success(String msg) {
        return new ResponseData(200, msg, null);

    }

}
