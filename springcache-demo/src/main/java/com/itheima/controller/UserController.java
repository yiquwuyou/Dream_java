package com.itheima.controller;

import com.itheima.entity.User;
import com.itheima.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    // cacheNames对应key的前半部分，右半部分是#user.id对应的数 user是传过来的参数，名称要一致
//    @CachePut(cacheNames = "userCache", key = "#user.id")    // 如果使用Spring Cache缓存数据，key的生成： userCache::2
    // result 表示该方法返回的值，再取id
//    @CachePut(cacheNames = "userCache", key = "#result.id")    // 对象导航
    // p0表示传过来的第一个参数，下面的a0和p0意思一致
//    @CachePut(cacheNames = "userCache", key = "#p0.id")
//    @CachePut(cacheNames = "userCache", key = "#a0.id")
    // 也是表示传来过的第一个参数，再取id
    @CachePut(cacheNames = "userCache", key = "#root.args[0].id")

    public User save(@RequestBody User user){
        userMapper.insert(user);
        return user;
    }

    @DeleteMapping
    @CacheEvict(cacheNames = "userCache", key = "#id")   // key的生成： userCache::10
    public void deleteById(Long id){
        userMapper.deleteById(id);
    }

	@DeleteMapping("/delAll")
    @CacheEvict(cacheNames = "userCache", allEntries = true)
    public void deleteAll(){
        userMapper.deleteAll();
    }

    @GetMapping
    // 此处就不能用result了，因为该注解是在方法执行前进行
    @Cacheable(cacheNames = "userCache", key = "#id")   // key的生成： userCache::10
    public User getById(Long id){
        User user = userMapper.getById(id);
        return user;
    }

}
