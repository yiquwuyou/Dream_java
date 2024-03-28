package com.example.questionnaire.mapper;

import com.example.questionnaire.model.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {

    // 查看所有系统公告
    @Select("SELECT * FROM notice ORDER BY create_time DESC")
    List<Notice> selectNotice();

    // 添加系统公告
    @Insert("insert into notice(uuid, title, content) values(#{uuid}, #{title}, #{content})")
    void insertNotice(String uuid, String title, String content);

    // 删除系统公告
    @Delete("delete from notice where uuid = #{uuid}")
    void deleteNotice(String uuid);

    // 查看最后一条公告
    @Select("SELECT * FROM notice ORDER BY create_time DESC LIMIT 1")
    Notice selectLastNotice();
}
