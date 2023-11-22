package com.yiquwuyou.springbootdemo1.mapper;

import com.yiquwuyou.springbootdemo1.model.MessageInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Insert("insert into message_info(`from`,`to`,`message`) values (#{from},#{to},#{message})")
    public void insertMessage(MessageInfo messageInfo);

    @Select("select * from message_info where delete_flag=0")
    List<MessageInfo> selectAllMessage();
}
