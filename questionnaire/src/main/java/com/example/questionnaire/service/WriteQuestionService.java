package com.example.questionnaire.service;

import com.example.questionnaire.mapper.WriteQuestionMapper;
import com.example.questionnaire.model.WriteQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WriteQuestionService {

    @Autowired
    private WriteQuestionMapper writeQuestionMapper;

    // 添加选项
    public void addQuestion(WriteQuestion writeQuestion) {

        writeQuestionMapper.addQuestion(writeQuestion);
    }

    // 删除填写过的整个问卷所有选项
    public Integer deleteQuestionByUserquestionId(String userquestionid, String writeusername) {
        return writeQuestionMapper.deleteQuestionByUserquestionId(userquestionid, writeusername);
    }

    // 查询该问卷自己填写的信息
    public List<WriteQuestion> getAllQuestions(String userquestionid, String writeusername) {

        return writeQuestionMapper.getAllQuestions(userquestionid, writeusername);
    }
}

