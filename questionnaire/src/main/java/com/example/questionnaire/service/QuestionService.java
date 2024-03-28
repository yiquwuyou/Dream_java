package com.example.questionnaire.service;

import com.example.questionnaire.mapper.QuestionMapper;
import com.example.questionnaire.model.QuestionModel;
import com.example.questionnaire.model.SingleQuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service层处理Question相关业务逻辑
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 添加问题
     * @param question 要添加的问题对象
     */
    public void addQuestion(SingleQuestionModel question) {
        questionMapper.addQuestion(question);
    }

    /**
     * 根据问卷id删除问题
     * @param userquestionid 问卷id
     */
    public void deleteQuestionByUserquestionId(String userquestionid) {
        questionMapper.deleteQuestionByUserquestionId(userquestionid);
    }

    /**
     * 根据选项id删除选项
     * @param id 问题id
     */
    public void deleteQuestionById(String id) {
        questionMapper.deleteQuestionById(id);
    }

    /**
     * 更新选项
     * @param question 要更新的问题对象
     */
    public void updateQuestion(SingleQuestionModel question) {
        questionMapper.updateQuestion(question);
    }

    /**
     * 获取所有选项信息
     * @return 所有问题信息的QuestionModel对象
     */
    public List<SingleQuestionModel> getAllQuestions(String userquestionid) {
        return questionMapper.getAllQuestions(userquestionid);
    }
}