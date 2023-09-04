package com.example.demo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.omg.CORBA.OMGVMCID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// 保底统一返回数据格式的封装
// 当返回的数据不是 AjaxResult 的时候转换成 AjaxResult
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {

    @Autowired
    private ObjectMapper objectMapper;

    // true 表示该转换器将支持任何返回类型和转换器类型
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof AjaxResult)
            return body;
        if (body instanceof String) {
            return objectMapper.writeValueAsString(AjaxResult.success(body));
        }
        return AjaxResult.success(body);
    }

//    // todo: 搞清楚为啥这个方法这样写时，注册和登录会错误 但是注册实际成功了，前端显示错误，然后登录是登陆不上去
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//
//        return null;
//    }
}
