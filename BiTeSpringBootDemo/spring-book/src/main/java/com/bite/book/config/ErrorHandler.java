package com.bite.book.config;

import com.bite.book.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Slf4j
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    public Result excption(NullPointerException e){
        log.error("发生异常,e:",e);
        return Result.fail("NullPointerException异常, 请联系管理员");
    }

    @ExceptionHandler
    public Result excption(ArithmeticException e){
        log.error("发生异常,e:",e);
        return Result.fail("ArithmeticException 异常");
    }
    @ExceptionHandler
    public Result excption(Exception e){
        log.error("发生异常,e:",e);
        return Result.fail("内部错误");
    }

}
