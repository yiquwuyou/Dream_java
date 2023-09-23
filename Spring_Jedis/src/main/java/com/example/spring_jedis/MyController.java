package com.example.spring_jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

// 后续 Redis 测试的各种方法，都通过这个 Controller 提供的
@RestController
public class MyController {
    @Autowired
    private StringRedisTemplate redisTemplate;
}
