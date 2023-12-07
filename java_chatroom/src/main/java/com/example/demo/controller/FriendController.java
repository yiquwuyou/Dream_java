package com.example.demo.controller;

import com.example.demo.mapper.FriendMapper;
import com.example.demo.model.AddFriend;
import com.example.demo.model.Friend;
import com.example.demo.model.User;

import com.example.demo.model.UserJwt;
import com.example.demo.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "获取好友列表")
@CrossOrigin(origins="*")
public class FriendController {
    @Resource
    private FriendMapper friendMapper;

    @Autowired
    private FriendService friendService;

//    @ApiOperation(value = "获取好友列表")
    @GetMapping("/friendList")
    @ResponseBody
    public Object getFriendList(HttpServletRequest request) {
        // 1、从 token 中获取用户信息
        User user = new User();
        user.setUserId(UserJwt.getUserId());

        // 2、根据 userId 从数据库查数据即可
        List<Friend> friendList = friendMapper.selectFriendList(user.getUserId());
        return friendList;
    }

    @ApiOperation(value = "加好友时查询用户信息")
    @PostMapping("/selectFriend")
    @ResponseBody
    public User selectFriend(String username){
        User user = friendService.selectFriend(username);
        user.setPassword("");
        return user;
    }

    // 查询所有的加好友信息进行展示
    @ApiOperation(value = "查询所有的加好友信息进行展示")
    @PostMapping("/selectAddFriendList")
    @ResponseBody
    public List<AddFriend> selectAddFriendList(){
        // 从 token 中获取用户信息
        User user = new User();
        user.setUserId(UserJwt.getUserId());
        user.setUsername(UserJwt.getUsername());
        // 代码运行到这里说明用户登录状态正常
        List<AddFriend> addFriendList = friendService.selectAddFriendList(user.getUserId());
        return addFriendList;
    }


}
