package com.example.demo.mapper;

import com.example.demo.model.AddFriend;
import com.example.demo.model.Friend;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendMapper {
    List<Friend> selectFriendList(int userId);

    // 要获取插入后的自增主键，你需要在 @Insert 注解的 SQL 语句后面添加 useGeneratedKeys = true, keyProperty = "id"。
    // 这样，MyBatis 就会使用 JDBC 的 getGeneratedKeys 方法来获取数据库生成的主键，并将其设置到 addFriend 对象的 id 属性中。
    // 友情提示，Options没真正在这里用到，想使用前先验证一下能否真正获取到
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into add_friend(fromId,fromName, fromNickname, fromAvatar,toId,toName, toNickname, toAvatar, requestMessage, isAgree) " +
            "values(#{fromId},#{fromName}, #{fromNickname}, #{fromAvatar},#{toId},#{toName}, #{toNickname}, #{toAvatar}, #{requestMessage}, #{isAgree})")
    Integer addFriend(AddFriend addFriend);

    @Update("update add_friend set isAgree = #{isAgree} where fromId = #{fromId} and toId = #{toId}")
    Integer updateAgreeFriend(Integer isAgree, Integer fromId, Integer toId);

    @Insert("insert into friend(userId,friendId) values(#{userId},#{friendId}), (#{friendId},#{userId})")
    Integer insertfriend(int userId, int friendId);

    @Select("select * from add_friend where toId = #{userId} or fromId = #{userId}")
    List<AddFriend> selectAddFriendList(Integer userId);

}
