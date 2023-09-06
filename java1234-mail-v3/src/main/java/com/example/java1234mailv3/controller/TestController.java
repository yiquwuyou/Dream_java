package com.example.java1234mailv3.controller;

import com.example.java1234mailv3.entity.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 测试
@RestController
@RequestMapping("/java1234")
public class TestController {
    // 测试
    @GetMapping("/test")
    public R test() {
        return R.ok("123456789");
    }
}
