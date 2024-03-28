package com.example.questionnaire.controller;

import com.example.questionnaire.model.*;
import com.example.questionnaire.service.UserLogService;
import com.example.questionnaire.service.UserQuestionService;
import com.example.questionnaire.service.WriteQuestionService;
import com.example.questionnaire.utils.QuestionWriteConverter;
import com.example.questionnaire.utils.SingleQuestionConverter;
import com.example.questionnaire.utils.WriteQuestionConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/writequestion")
@CrossOrigin(origins="*")
@Slf4j
public class WriteQuestionController {

    @Autowired
    private WriteQuestionService writeQuestionService;

    @Autowired
    private UserQuestionService userQuestionService;

    @Autowired
    private UserLogService userLogService;

    // 添加
    @RequestMapping("/add")
    public Result addQuestion(@RequestBody QuestionnaireRequest questionList) {
        log.info("添加问卷选项, questionList:{}", questionList);
        // 循环
        for (int i = 0; i < questionList.getQuestions().size(); i++) {
            UUID longuuid = UUID.randomUUID();
            String id = longuuid.toString().substring(0, 8);
            String originalname = userQuestionService.getUsernameByUuid(questionList.getQuestions().get(i).getUserquestionid());
            log.info("添加问卷选项, originalname:{}", originalname);
            log.info("添加问卷选项, originalname:{}", originalname);
            WriteQuestion writeQuestion = WriteQuestionConverter.convert(questionList.getQuestions().get(i),
                    id, originalname, UserJwt.getUsername() ,1);
            log.info("添加问卷选项, writeQuestion:{}", writeQuestion);
            writeQuestionService.addQuestion(writeQuestion);
        }

        return Result.success("添加成功");
    }

    // 删除
    @RequestMapping("/delete")
    public Integer deleteQuestionByUserquestionId(@RequestBody Map<String, String> mapParams) {
        String userquestionid = mapParams.get("userquestionid");
        return writeQuestionService.deleteQuestionByUserquestionId(userquestionid, UserJwt.getUsername());
    }

    // 修改
    @RequestMapping("/update")
    public Result updateQuestion(@RequestBody QuestionnaireRequest questionList) {
        log.info("添加问卷选项, questionList:{}", questionList);
        writeQuestionService.deleteQuestionByUserquestionId(
                questionList.getQuestions().get(0).getUserquestionid(),
                UserJwt.getUsername());
        // 循环
        for (int i = 0; i < questionList.getQuestions().size(); i++) {
            UUID longuuid = UUID.randomUUID();
            String id = longuuid.toString().substring(0, 8);
            String originalname = userQuestionService.getUsernameByUuid(questionList.getQuestions().get(i).getUserquestionid());
            log.info("添加问卷选项, originalname:{}", originalname);
            WriteQuestion writeQuestion = WriteQuestionConverter.convert(questionList.getQuestions().get(i),
                    id, originalname, UserJwt.getUsername() ,1);
            log.info("添加问卷选项, writeQuestion:{}", writeQuestion);
            writeQuestionService.addQuestion(writeQuestion);
        }
        userQuestionService.updateAnswerNumber(questionList.getQuestions().get(0).getUserquestionid());

        // 日志
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 使用格式化输出当前日期和时间，带上年月日时分秒关键词
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日的HH时mm分ss秒");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);

        // 我修改了谁的问卷，问卷的uuid是多少
        String content = "您于" + formattedDateTime + "修改了问卷《" + questionList.getQuestions().get(0).getTitle() + "》"+
                ", 该问卷uuid为"+questionList.getQuestions().get(0).getUserquestionid();
        userLogService.insertUserLog(UserJwt.getUsername(), 4, content);

        // 谁修改了我的问卷，问卷的uuid是多少
        content = "您的问卷《" + questionList.getQuestions().get(0).getTitle() + "》"+
                "被"+UserJwt.getUsername()+"修改了"+", 该问卷uuid为"+questionList.getQuestions().get(0).getUserquestionid();
        String originalname = userQuestionService.getUsernameByUuid(questionList.getQuestions().get(0).getUserquestionid());
        userLogService.insertUserLog(originalname, 5, content);
        return Result.success("修改成功");
    }


    // 获取自己填写的这个问卷的信息
    @RequestMapping("/getAll")
    public Result getAllQuestions(@RequestBody Map<String, String> mapParams) {
        String userquestionid = mapParams.get("userquestionid");
        List<WriteQuestion> writeQuestions = writeQuestionService.getAllQuestions(userquestionid, UserJwt.getUsername());
        List<Question> questions = new ArrayList<>();
        for (WriteQuestion writeQuestion : writeQuestions) {
            questions.add(QuestionWriteConverter.convert(writeQuestion));
        }
        return Result.success(questions);
    }
}
