package com.example.demo.model;

import com.example.demo.enums.ResultCode;
import lombok.Data;

/**
 *  返回结果类
 *  统一封装返回结果
 */

@Data
public class Result<T> {
    // 业务状态码
    private ResultCode code;  // 0 成功  -1 失败   -2 登录 (会自动转化为枚举常量)

    // 错误信息
    private String errMsg;

    // 数据
    private T data;

    // 返回成功响应，正确数据
    public static <T> Result<T> success(T data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        result.setErrMsg("");
        result.setData(data);
        return result;
    }

    // 返回失败响应，错误信息，data数据为空
    public static <T> Result<T> fail(String errMsg){
        Result result = new Result();
        result.setCode(ResultCode.FAIL);
        result.setErrMsg(errMsg);
        result.setData(null);
        return result;
    }

    // 返回失败响应，错误信息，错误数据data
    public static <T> Result<T> fail(String errMsg,Object data){
        Result result = new Result();
        result.setCode(ResultCode.FAIL);
        result.setErrMsg(errMsg);
        result.setData(data);
        return result;
    }

    // 用户未登录
    public static <T> Result<T> unlogin(){
        Result result = new Result();
        result.setCode(ResultCode.UNLOGIN);
        result.setErrMsg("用户未登录");
        result.setData(null);
        return result;
    }
}
