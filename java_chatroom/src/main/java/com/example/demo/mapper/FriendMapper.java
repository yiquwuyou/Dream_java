package com.example.demo.mapper;

import com.example.demo.model.Friend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendMapper {
    List<Friend> selectFriendList(int userId);
}
