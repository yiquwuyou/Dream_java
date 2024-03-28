package com.example.questionnaire.mapper;

import com.example.questionnaire.model.SingleQuestionModel;
import com.example.questionnaire.model.WriteQuestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WriteQuestionMapper {

    // 添加选项
    void addQuestion(WriteQuestion writeQuestion);


    // 删除填写过的整个问卷所有选项
    @Delete("DELETE FROM writequestion WHERE userquestionid = #{userquestionid} " +
            "and writeusername = #{writeusername}")
    Integer deleteQuestionByUserquestionId(String userquestionid, String writeusername);



    // 查询该问卷自己填写的信息
    @Select("SELECT * FROM writequestion WHERE userquestionid = #{userquestionid} " +
            "and writeusername = #{writeusername}")
    List<WriteQuestion> getAllQuestions(String userquestionid, String writeusername);

    // 查看用户填写过的问卷
    @Select("SELECT * FROM writequestion WHERE writeusername = #{writeusername}")
    List<WriteQuestion> getAllQuestionsByWriteusername(String writeusername);
}
