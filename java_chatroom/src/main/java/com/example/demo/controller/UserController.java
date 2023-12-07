package com.example.demo.controller;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserJwt;
import com.example.demo.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户相关接口")
@RestController
@CrossOrigin(origins="*")
@Slf4j
public class UserController {

    // 操作数据库，创建 mapper实例
    @Resource
    UserMapper userMapper;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    @ResponseBody
    public Object login(String username, String password){
        // 1、先去数据库中查看，看 username 能否找到对应的 user 对象
        //    如果能找到则看一下密码是否匹配
        User user =userMapper.selectByName(username);
        if (user == null || !user.getPassword().equals(password)) {
            // 这俩条件具备一个，就是登录失败！！同时返回一个空的对象即可
            System.out.println("登陆失败！用户名或者密码错误！" + user);
            return new User();
        }
        Map<String, String> map = new HashMap<>();
        map.put("userId", String.valueOf(user.getUserId()));
        map.put("username", user.getUsername());
        String token = JwtUtil.getToken(map);
        map.put("token", token);
        log.info("用户已登录：" + map);
        return map;

    }

    // 注册
    @RequestMapping("/register")
    @ResponseBody
    @ApiOperation(value = "注册")
    public Object register(String username, String password){
        User user = new User();
        try {
            user.setUsername(username);
            user.setPassword(password);
            int ret = userMapper.insert(user);
            log.info("注册 ret：" + ret);
            user.setPassword("");
        } catch (DuplicateKeyException e) {
            // 如果 insert 方法抛出上述异常，说明名字重复了，注册失败
            user = new User();
            log.info("注册失败! username = " + username);
        }
        return user;
    }

    // 获取登录用户的信息
    @GetMapping("/userInfo")
    @ResponseBody
    @ApiOperation(value = "获取登录用户的信息")
    public Object getUserInfo(HttpServletRequest request) {
        // 从 token 中获取用户信息
        User user = new User();
        user.setUserId(UserJwt.getUserId());
        user.setUsername(UserJwt.getUsername());
        return user;
    }
}
