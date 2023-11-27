package com.example.demo.mapper;

import com.example.demo.model.AddFriend;
import com.example.demo.model.Friend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FriendMapper {
    List<Friend> selectFriendList(int userId);

    @Insert("insert into add_friend(fromId,fromName,toId,toName,isAgree) " +
            "values(#{fromId},#{fromName},#{toId},#{toName},#{isAgree})")
    Integer addFriend(AddFriend addFriend);

    @Update("update add_friend set isAgree = #{isAgree} where id = #{id}")
    Integer updateAgreeFriend(AddFriend addFriend);

    @Insert("insert into friend(userId,friendId) values(#{userId},#{friendId}), (#{friendId},#{userId})")
    Integer insertfriend(int userId, int friendId);

    @Select("select * from add_friend where toId = #{userId} or fromId = #{userId}")
    List<AddFriend> selectAddFriendList(Integer userId);

}
