package com.example.demo.controller;

import com.example.demo.mapper.FriendMapper;
import com.example.demo.model.AddFriend;
import com.example.demo.model.Friend;
import com.example.demo.model.User;

import com.example.demo.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "获取好友列表")
public class FriendController {
    @Resource
    private FriendMapper friendMapper;

    @Autowired
    private FriendService friendService;

//    @ApiOperation(value = "获取好友列表")
    @GetMapping("/friendList")
    @ResponseBody
    public Object getFriendList(HttpServletRequest request) {
        // 1、先从会话中，获取到 userId 是啥
        HttpSession session = request.getSession(false);
        if (session == null) {
            // 当前用户会话不存在
            // yiquwuyou 专属——1
            // 直接返回一个空的列表即可
            System.out.println("[getFriendList] session 不存在");
            return new ArrayList<Friend>();
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 当前用户对象没在会话中存在
            System.out.println("[getFriendList] user 不存在");
            return new ArrayList<Friend>();
        }
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
    public List<AddFriend> selectAddFriendList(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            // 当前用户会话不存在
            // 直接返回一个空的列表即可
            log.info("[getFriendList] session 不存在");
            return new ArrayList<AddFriend>();
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 当前用户对象没在会话中存在
            log.info("[getFriendList] user 不存在");
            return new ArrayList<AddFriend>();
        }
        // 代码运行到这里说明用户登录状态正常
        List<AddFriend> addFriendList = friendService.selectAddFriendList(user.getUserId());
        return addFriendList;
    }


}
