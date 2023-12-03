package com.bite.book.controller;

import com.bite.book.model.BookInfo;
import com.bite.book.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping("/t1")
    public Boolean t1(){
        int a= 10/0;
        return true;
    }

    @RequestMapping("/t2")
    public Integer t2(){
        String a = null;
        System.out.println(a.length());
        return 1233;
    }

    @RequestMapping("/t3")
    public String t3(){
        int[] a = {1,2,3,4};
        System.out.println(a[4]);
        return "hello";
    }

    @RequestMapping("/t4")
    public BookInfo t4(){
        return new BookInfo();
    }

    @RequestMapping("/t5")
    public Result t5(){
        return Result.success("success");
    }
}
