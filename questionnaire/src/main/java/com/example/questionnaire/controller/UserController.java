package com.example.questionnaire.controller;

import com.example.questionnaire.model.Result;
import com.example.questionnaire.model.User;
import com.example.questionnaire.model.UserJwt;
import com.example.questionnaire.service.UserService;
import com.example.questionnaire.utils.JwtUtil;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins="*")
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    // 登录
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestBody Map<String, String> mapParams){
        String username = mapParams.get("username");
        String password = mapParams.get("password");

        //校验参数
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)){
            return Result.fail("用户名或密码不能为空");
        }
        //验证账号密码是否正确
        /**
         * if (userName.equals("admin")){ }  这种写法, 如果userName为null, 会报空指针异常
         * 开发习惯, 养成
         */
        //1. 根据用户名去查找用户信息
        User user = userService.selectByName(username);
        //2. 比对密码是否正确
        if (user == null || user.getUserId() <= 0){
            return Result.fail("用户名不存在");
        }
        if (!password.equals(user.getPassword())){
            //账号密码不正确(不相等)
            return Result.fail("密码错误");
        }
        // 来到这里才说明一切都没问题
        Map<String, String> map = new HashMap<>();
        map.put("userId", String.valueOf(user.getUserId()));
        map.put("username", user.getUsername());
        Map<String, String> mapJWT = new HashMap<>(map);
        String token = JwtUtil.getToken(mapJWT);
        map.put("token", token);
        log.info("用户已登录：" + map);
        return Result.success(map);

    }
    // 注册
    @RequestMapping("/register")
    public Result register(@RequestBody Map<String, String> mapParams){
        String username = mapParams.get("username");
        String password = mapParams.get("password");
//        校验参数
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)){
            return Result.fail("用户名或密码不能为空");
        }
        //校验用户名是否存在
        User user = userService.selectByName(username);
        if (user != null && user.getUserId() > 0){
            return Result.fail("用户名已存在");
        }
        //注册
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int ret = userService.insertUser(user);
        if (ret > 0){
            User user1 = userService.selectByName(username);
            return Result.success(user1);
        }
        return Result.fail("注册失败");
    }

    // 修改用户信息
    @RequestMapping("/update")
    public Result update(@RequestBody User user){
        String username = UserJwt.getUsername();
        user.setUsername(username);
        //修改
        int ret = userService.updateUser(user);
        if (ret > 0){
            return Result.success(userService.selectByName(username));
        }
        return Result.fail("修改失败");
    }

    // 查询用户信息
    @RequestMapping("/selectByName")
    public Result selectByName(){
        String username = UserJwt.getUsername();
        // 参数校验
        if (!StringUtils.hasLength(username)){
            return Result.fail("用户名不能为空");
        }
        //查询
        User user = userService.selectByName(username);
        if (user != null && user.getUserId() > 0){
            return Result.success(user);
        }
        return Result.fail("查询失败");
    }


    // 查询原密码
    @RequestMapping("/selectPassword")
    public Result selectPassword(){
        String username = UserJwt.getUsername();
        log.info("username: " + username);
        // 参数校验
        if (!StringUtils.hasLength(username)){
            return Result.fail("用户名不能为空");
        }
        //查询
        String password = userService.selectPassword(username);
        if (password != null){
            return Result.success(password);
        }
        return Result.fail("查询失败");
    }

    // 修改密码
    @RequestMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String, String> mapParams){
        String username = UserJwt.getUsername();
        String originalPassword = mapParams.get("originalPassword");
        String newPassword = mapParams.get("newPassword");

        // 参数校验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(originalPassword) || !StringUtils.hasLength(newPassword)){
            return Result.fail("用户名或密码不能为空");
        }
        // 对比密码是否正确
        String truePassword = userService.selectPassword(username);
        if (!originalPassword.equals(truePassword)){
            return Result.fail("原密码错误");
        }
        // 修改密码
        int ret = userService.updatePassword(username, newPassword);
        if (ret > 0){
            return Result.success("修改成功");
        }
        return Result.fail("修改失败");
    }
}
