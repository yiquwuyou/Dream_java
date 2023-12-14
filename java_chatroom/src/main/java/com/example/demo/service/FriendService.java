package com.example.demo.service;


import com.example.demo.mapper.FriendMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.AddFriend;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FriendService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FriendMapper friendMapper;

    public User selectFriend(String username){
        return userMapper.selectByName(username);
    }

    public void addFriendAn(AddFriend addFriend){
        friendMapper.addFriend(addFriend);
    }

    public void updateAgreeFriend(Integer isAgree, Integer fromId, Integer toId){
        friendMapper.updateAgreeFriend(isAgree,fromId,toId);
    }

    public void insertfriend(int userId, int friendId){
        friendMapper.insertfriend(userId,friendId);
    }

    public List<AddFriend> selectAddFriendList(Integer userId){
        return friendMapper.selectAddFriendList(userId);
    }

}
