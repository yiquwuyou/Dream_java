package com.example.questionnaire.controller;


import com.example.questionnaire.model.*;
import com.example.questionnaire.service.QuestionService;
import com.example.questionnaire.utils.QuestionConverter;
import com.example.questionnaire.utils.SingleQuestionConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Controller层处理Question相关请求
 */
@RestController
@RequestMapping("/question")
@CrossOrigin(origins="*")
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 添加整个问卷
     * @param
     */
    @RequestMapping("/add")
    public Result addQuestion(@RequestBody QuestionnaireRequest questionList) {
        // 循环
        for (int i = 0; i < questionList.getQuestions().size(); i++) {
            UUID longuuid = UUID.randomUUID();
            String id = longuuid.toString().substring(0, 8);
            SingleQuestionModel singleQuestionModel = SingleQuestionConverter.convert(questionList.getQuestions().get(i), id, UserJwt.getUsername());
            log.info("添加问卷选项, singleQuestionModel:{}", singleQuestionModel);
            questionService.addQuestion(singleQuestionModel);
        }
        return Result.success("添加成功");
    }

    /**
     * 根据问卷id删除问题
     * @param
     */
    @RequestMapping("/deleteAll")
    public Result deleteQuestionByUserquestionId(@RequestBody Map<String, String> mapParams) {
        String userquestionid = mapParams.get("userquestionid");
        questionService.deleteQuestionByUserquestionId(userquestionid);
        return Result.success("删除成功");
    }

    /**
     * 根据选项id删除选项
     * @param
     */
    @RequestMapping("/delete")
    public Result deleteQuestionById(@RequestBody Map<String, String> mapParams) {
        String id = mapParams.get("id");
        questionService.deleteQuestionById(id);
        return Result.success("删除成功");
    }

    /**
     * 更新选项
     * @param question 要更新的问题对象
     */
    @RequestMapping("/update")
    public Result updateQuestion(@RequestBody Question question) {
        SingleQuestionModel singleQuestionModel = SingleQuestionConverter.convert(question, question.getId(), UserJwt.getUsername());
        questionService.updateQuestion(singleQuestionModel);
        return Result.success("更新成功");
    }
//
    /**
     * 获取所有选项信息
     * @return 所有问题信息的QuestionModel对象
     */
    @RequestMapping("/getAllQuestions")
    public Result getAllQuestions(@RequestBody Map<String, String> mapParams) {
        String userquestionid = mapParams.get("userquestionid");
        List<SingleQuestionModel> singleQuestionModels = questionService.getAllQuestions(userquestionid);
        List<Question> questions = new ArrayList<>();
        for (SingleQuestionModel singleQuestionModel : singleQuestionModels) {
            Question question = QuestionConverter.convert(singleQuestionModel);
            question.setUserquestionid(userquestionid);
            questions.add(question);
        }
        return Result.success(questions);
    }
}

