package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "用户相关接口")
@RestController
public class UserController {

    // 操作数据库，创建 mapper实例
    @Resource
    UserMapper userMapper;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    @ResponseBody
    // 用 HttpServletRequest request 来准备创建 会话 ！！
    public Object login(String username, String password, HttpServletRequest request){
        // 1、先去数据库中查看，看 username 能否找到对应的 user 对象
        //    如果能找到则看一下密码是否匹配
        User user =userMapper.selectByName(username);
        if (user == null || !user.getPassword().equals(password)) {
            // 这俩条件具备一个，就是登录失败！！同时返回一个空的对象即可
            System.out.println("登陆失败！用户名或者密码错误！" + user);
            return new User();
        }

        // 2、如果都匹配，登录成功！创建会话！！
        // 之后便可以用 会话 存储用户登录信息了
        HttpSession session = request.getSession(true);
        // 将名为 “user” 的属性与一个 “user” 对象绑定到会话中
        session.setAttribute("user", user);
        // 再返回之前，把 password 给干掉，避免返回不必要的信息，更安全
        user.setPassword("");
        System.out.println("666666666666666666--------666666666");
        return user;
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
            System.out.println("注册 ret：" + ret);
            user.setPassword("");
        } catch (DuplicateKeyException e) {
            // 如果 insert 方法抛出上述异常，说明名字重复了，注册失败
            user = new User();
            System.out.println("注册失败! username = " + username);
        }
        return user;
    }

    // 获取登录用户的信息
    @GetMapping("/userInfo")
    @ResponseBody
    @ApiOperation(value = "获取登录用户的信息")
    public Object getUserInfo(HttpServletRequest request) {
        // 1、先从请求中获取到对话
        HttpSession session = request.getSession(false);
        if (session == null){
            // 会话不存在，用户尚未登录，此时返回一个空的对象即可
            System.out.println("[getUserInfo] 当前获取不到 session 对象！");
            return new User();
        }
        // 2、从会话中获取到之前保存的用户对象
        User user = (User) session.getAttribute("user");
        if (user== null) {
            System.out.println("[getUserInfo] 当前获取不到 user 对象！");
            return new User();
        }
        user.setPassword("");
        return user;
    }
}
