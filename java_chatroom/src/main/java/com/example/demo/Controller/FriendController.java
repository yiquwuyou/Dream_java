package com.example.demo.Controller;

import com.example.demo.mapper.FriendMapper;
import com.example.demo.model.Friend;
import com.example.demo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendController {
    @Resource
    private FriendMapper friendMapper;

    @GetMapping("/friendList")
    @ResponseBody
    public Object getFriendList(HttpServletRequest request) {
        // 1、先从会话中，获取到 userId 是啥
        HttpSession session = request.getSession(false);
        if (session == null) {
            // 当前用户会话不存在
            // yiquwuyou 专属
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
}
