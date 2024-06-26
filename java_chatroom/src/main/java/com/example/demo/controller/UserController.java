package com.example.demo.controller;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Result;
import com.example.demo.model.User;
import com.example.demo.model.UserJwt;
import com.example.demo.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户相关接口")
@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

    // 操作数据库，创建 mapper实例
    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    @ResponseBody
    public Object login(String username, String password) {
        // 1、先去数据库中查看，看 username 能否找到对应的 user 对象
        //    如果能找到则看一下密码是否匹配
        User user = userMapper.selectByName(username);
        if (user == null || !user.getPassword().equals(password)) {
            // 这俩条件具备一个，就是登录失败！！同时返回一个空的对象即可
            log.info("登陆失败！用户名或者密码错误！" + user);
            return new User();
        }
        Map<String, String> map = new HashMap<>();
        map.put("userId", String.valueOf(user.getUserId()));
        map.put("username", user.getUsername());
        Map<String, String> mapJWT = new HashMap<>(map);
        String token = JwtUtil.getToken(mapJWT);
        map.put("nickname", user.getNickname());
        map.put("avatarPath", user.getAvatarPath());
        map.put("signature", user.getSignature());
        map.put("sex", user.getSex());
        map.put("token", token);
        log.info("用户已登录：" + map);
        return map;

    }

    // 注册
    @RequestMapping("/register")
    @ResponseBody
    @ApiOperation(value = "注册")
    public Object register(String username, String password) {
        User user = new User();
        try {
            user.setUsername(username);
            user.setPassword(password);
            // 先插入 mysql - 需要用到UserId
            int ret = userMapper.insert(user);

            // 再插入 redis
            HashOperations hashOperations = redisTemplate.opsForHash();
            log.info("----------------------------------------------------------");
            String key = "UserId" + user.getUserId();
            hashOperations.put(key, "username", user.getUsername());
            hashOperations.put(key, "nickname", user.getNickname());
            hashOperations.put(key, "avatarPath", user.getAvatarPath());
            hashOperations.put(key, "signature", user.getSignature());
            hashOperations.put(key, "sex", user.getSex());

            log.info("注册 ret：" + ret);
            user.setPassword("");
        } catch (DuplicateKeyException e) {
            // 如果 insert 方法抛出上述异常，说明名字重复了，注册失败
            user = new User();
            log.info("注册失败! username = " + username);
            return Result.fail("注册失败，该用户已经存在！");
        }
        return user;
    }

    // 获取登录用户的信息
    @GetMapping("/userInfo")
    @ResponseBody
    @ApiOperation(value = "获取登录用户的信息")
    public Object getUserInfo(HttpServletRequest request) {
        User user = new User();

        // 先在 redis 中查询
        // 如果 redis 中有，直接返回
        String key = "UserId" + UserJwt.getUserId();
        if(redisTemplate.hasKey(key)){
            HashOperations hashOperations = redisTemplate.opsForHash();
            user.setUserId(UserJwt.getUserId());
            user.setUsername(UserJwt.getUsername());
            user.setNickname((String) hashOperations.get(key, "nickname"));
            user.setAvatarPath((String) hashOperations.get(key, "avatarPath"));
            user.setSignature((String) hashOperations.get(key, "signature"));
            user.setSex((String) hashOperations.get(key, "sex"));
            log.info("Redis 查询用户信息：" + user);
            return user;
        }

        // 如果 redis 中没有，再在 mysql 数据库中查询
        // 从 token 中获取用户信息
        user = userMapper.selectByName(UserJwt.getUsername());
        log.info("mysql 查询用户信息：" + user);
        return user;
    }

    // 修改/更新用户信息
    @RequestMapping("/updateUser")
    @ResponseBody
    public Object updateUser(@RequestBody User user) {
        log.info("更新用户信息：" + user);
        if (UserJwt.getUserId() != user.getUserId()) {
            log.info("用户id不匹配，无法更新用户信息");
            return null;
        }

        // 先更新 redis
        HashOperations hashOperations = redisTemplate.opsForHash();
        String key = "UserId" + user.getUserId();
//        hashOperations.put(key, "username", user.getUsername());
        hashOperations.put(key, "nickname", user.getNickname());
        hashOperations.put(key, "avatarPath", user.getAvatarPath());
        hashOperations.put(key, "signature", user.getSignature());
        hashOperations.put(key, "sex", user.getSex());

        // 再更新 mysql
        int ret = userMapper.updateUser(user);
        log.info("更新用户信息 ret：" + ret);
        return ret;
    }


//    // 下面两个是测试接口,后续删除
//    @ResponseBody
////    @GetMapping(value = "/ceshi",produces="text/plain;charset=UTF-8")
//    @GetMapping(value = "/ceshi")
//    public String ceshi(){
//        String x = "测试通过";
//        log.info(x);
//        return x;
//    }
//
//    @ResponseBody
//    @GetMapping("/测试")
//    public String ceshi2(){
//        String x = "----cssac";
//        log.info(x);
//        return x;
//    }
}
