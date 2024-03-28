package com.example.questionnaire.mapper;

import com.example.questionnaire.model.Question;
import com.example.questionnaire.model.QuestionModel;
import com.example.questionnaire.model.SingleQuestionModel;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface QuestionMapper {
    // 添加选项
    void addQuestion(SingleQuestionModel question);

    // 根据问卷id删除问卷
    @Delete("DELETE FROM question WHERE userquestionid = #{userquestionid}")
    void deleteQuestionByUserquestionId(String userquestionid);

    // 根据选项id删除问卷选项
    @Delete("DELETE FROM question WHERE id = #{id}")
    void deleteQuestionById(String id);

    // 更新问卷选项

    void updateQuestion(SingleQuestionModel question);

    // 查询该问卷所有信息
    @Select("SELECT * FROM question WHERE userquestionid = #{userquestionid}")
    List<SingleQuestionModel> getAllQuestions(String userquestionid);
}
