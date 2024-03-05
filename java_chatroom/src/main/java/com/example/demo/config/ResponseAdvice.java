package com.example.demo.config;

import com.example.demo.model.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //在返回之前, 需要做的事情
        //body 是返回的结果
        if (body instanceof Result){
            return body;
        }
        if (body instanceof String){
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            log.info("返回前--" + body);
            log.info("Result返回前--" + Result.success(body));
            log.info("假装返回后--" + objectMapper.writeValueAsString(Result.success(body)));
            return objectMapper.writeValueAsString(Result.success(body));
        }
        return Result.success(body);
    }


}
