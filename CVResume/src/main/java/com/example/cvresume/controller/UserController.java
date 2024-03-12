package com.example.cvresume.controller;

import com.example.cvresume.model.Result;
import com.example.cvresume.model.User;
import com.example.cvresume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins="*")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 登录
    @RequestMapping("/login")
    public Result login(String username, String password){
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
            return Result.fail("账号或密码错误");
        }
        // 来到这里才说明一切都没问题
        return Result.success(user);

    }
    // 注册

}
