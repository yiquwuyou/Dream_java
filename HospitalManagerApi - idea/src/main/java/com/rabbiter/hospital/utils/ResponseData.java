package com.rabbiter.hospital.utils;


import java.io.Serializable;

// ResponseData 是一个通用的响应数据类，它包含了以下属性:

//status：表示响应的状态，通常使用 HTTP 状态码来表示。例如，200 表示成功，400 表示失败。
//
//        msg：表示响应的消息，用于描述响应的结果或提供额外的信息。
//
//        data：表示响应的数据，通常是一个对象或数据集，包含了具体的响应内容。
//
//        这个类还提供了一些静态方法来创建不同类型的响应对象：
//
//        success(String msg, Object data)：创建一个成功的响应对象，包括成功状态码（200）、消息和数据。
//
//        fail(String msg)：创建一个失败的响应对象，包括失败状态码（400）和消息，但没有数据。
//
//        这个类的主要作用是规范响应的格式和内容，以便在控制器中使用它来构建响应对象，然后将其返回给客户端。
//        这样可以提高代码的可维护性和可读性，使响应的格式变得一致和易于理解。
//        通常，客户端可以根据 status 字段来判断请求是否成功，然后使用 msg 和 data 来获取具体的响应信息和数据
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
