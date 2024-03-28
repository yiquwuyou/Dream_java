package com.example.questionnaire.mapper;

import com.example.questionnaire.model.UserQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserQuestionMapper {

    // 查询所有用户问卷(包括发布和未发布)
    @Select("SELECT * FROM userquestion order by id desc")
    List<UserQuestion> getAllUserQuestions();

    // 查询所有人已发布的问卷
    @Select("SELECT * FROM userquestion WHERE is_publish = 1")
    List<UserQuestion> getAllUserQuestionsPublish();

    // 根据问卷id查询问卷
    @Select("SELECT * FROM userquestion WHERE uuid = #{uuid}")
    UserQuestion getUserQuestionByuuId(String uuid);

    // 根据问卷uuid查作者
    @Select("SELECT username FROM userquestion WHERE uuid = #{uuid}")
    String getUsernameByUuid(String uuid);

    // 根据用户名查询问卷
    @Select("SELECT * FROM userquestion WHERE username = #{username}")
    List<UserQuestion> getUserQuestionsByUsername(String username);

    // 查询该用户所有发布的问卷
    @Select("SELECT * FROM userquestion WHERE username = #{username} and is_publish = 1")
    List<UserQuestion> getUserQuestionsPublish(String username);

    // 查询该用户所有未发布的问卷
    @Select("SELECT * FROM userquestion WHERE username = #{username} and is_publish = 0")
    List<UserQuestion> getUserQuestionsNoPublish(String username);

    // 插入新的用户问卷(返回id)
    @Insert("INSERT INTO userquestion (uuid, username, title, content, is_publish) " +
            "VALUES (#{uuid}, #{username}, #{title}, #{content}, #{isPublish})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertUserQuestion(UserQuestion userQuestion);

    // 更新用户问卷
    void updateUserQuestion(UserQuestion userQuestion);

    // 删除用户问卷
    @Delete("DELETE FROM userquestion WHERE uuid = #{uuid}")
    void deleteUserQuestion(String uuid);

    //删除用户所有问卷
    @Delete("DELETE FROM userquestion WHERE username = #{username}")
    int deleteUserQuestionsByUsername(String username);

    // 修改问卷发布状态
    @Update("UPDATE userquestion SET is_publish = #{isPublish} WHERE uuid = #{uuid}")
    void updatePublishStatus(@Param("uuid") String uuid, @Param("isPublish") int isPublish);

    // 问卷回答数加一
    @Update("UPDATE userquestion \n" +
            "SET answer_number = answer_number + 1 \n" +
            "WHERE uuid = #{uuid};\n")
    Integer updateAnswerNumber(@Param("uuid") String uuid);

    // 查询问卷回答数
    @Select("SELECT answer_number FROM userquestion WHERE uuid = #{uuid}")
    Integer selectAnswerNumber(@Param("uuid") String uuid);
}