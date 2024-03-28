package com.example.questionnaire.controller;

import com.example.questionnaire.model.*;
import com.example.questionnaire.service.QuestionService;
import com.example.questionnaire.service.UserQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 用户问卷控制器
 */
@RestController
@RequestMapping("/userquestion")
@CrossOrigin(origins="*")
@Slf4j
public class UserQuestionController {

    @Autowired
    private UserQuestionService userQuestionService;

    @Autowired
    private QuestionService questionService;

    /**
     * 获取所有用户问卷
     * @return 用户问卷列表
     */
    @RequestMapping("/selectAll")
    public Result getAllUserQuestions() {
        return Result.success(userQuestionService.getAllUserQuestions());
    }

    /**
     * 查询所有人已发布的问卷
     * @return 用户问卷列表
     */
    @RequestMapping("/selectAllPublish")
    public Result getAllUserQuestionsPublish() {
        return Result.success(userQuestionService.getAllUserQuestionsPublish());
    }

    /**
     * 根据问卷uuid查询问卷
     * @return 问卷对象
     */
    @RequestMapping("/selectId")
    public Result getUserQuestionById(@RequestBody Map<String, String> mapParams) {
        String uuid = mapParams.get("uuid");
        log.info("根据问卷uuid查询问卷:{}", uuid);
        return Result.success(userQuestionService.getUserQuestionByuuId(uuid));
    }

    /**
     * 查询该用户所有问卷
     */
    @RequestMapping("/selectUsername")
    public Result getUserQuestionsByUsername() {
        String username = UserJwt.getUsername();
        log.info("根据用户名查询问卷:{}", username);
        return Result.success(userQuestionService.getUserQuestionsByUsername(username));
    }

    /**
     * 插入新的用户问卷
     * @param userQuestion 用户问卷对象
     * @return 插入的用户问卷id
     */
    @RequestMapping("/insert")
    public Result insertUserQuestion(@RequestBody UserQuestion userQuestion) {
        log.info("插入新的用户问卷:{}", userQuestion);
        UUID longuuid = UUID.randomUUID();
        String uuid = longuuid.toString().substring(0, 8);
        userQuestion.setUuid(uuid);
        userQuestion.setUsername(UserJwt.getUsername());
        return Result.success(userQuestionService.insertUserQuestion(userQuestion));
    }

    /**
     * 更新用户问卷
     * @param userQuestion 用户问卷对象
     */
    @RequestMapping("/update")
    public Result updateUserQuestion(@RequestBody UserQuestion userQuestion) {
        log.info("更新用户问卷:{}", userQuestion);
        userQuestionService.updateUserQuestion(userQuestion);
        return Result.success("更新成功");
    }

    /**
     * 删除某一个问卷
     */
    @RequestMapping("/delete")
    public Result deleteUserQuestion(@RequestBody Map<String, String> mapParams) {
        String uuid = mapParams.get("uuid");
        userQuestionService.deleteUserQuestion(uuid);
        return Result.success("删除成功");
    }

    /**
     * 删除所有问卷
     */
    @RequestMapping("/deleteAll")
    public Result deleteAllUserQuestions() {

        int ret = userQuestionService.deleteAllUserQuestions(UserJwt.getUsername());
        return Result.success(ret);
    }

    /**
     * 查询该用户所有发布的问卷
     */
    @RequestMapping("/selectPublish")
    public Result getUserQuestionsPublish() {
        String username = UserJwt.getUsername();
        log.info("查询该用户所有发布的问卷:{}", username);
        return Result.success(userQuestionService.getUserQuestionsPublish(username));
    }

    /**
     * 查询该用户所有未发布的问卷
     */
    @RequestMapping("/selectNoPublish")
    public Result getUserQuestionsNoPublish() {
        String username = UserJwt.getUsername();
        log.info("查询该用户所有未发布的问卷:{}", username);
        return Result.success(userQuestionService.getUserQuestionsNoPublish(username));
    }

    /**
     * 修改问卷发布状态
     */
    @RequestMapping("/updatePublishStatus")
    public Result updatePublishStatus(@RequestBody Map<String, String> mapParams) {
        String uuid = mapParams.get("uuid");
        int isPublish = Integer.parseInt(mapParams.get("isPublish"));
        userQuestionService.updatePublishStatus(uuid, isPublish);
        return Result.success("修改成功");
    }

    /**
     * 问卷浏览数加一
     */
    @RequestMapping("/updateAnswerNumber")
    public Result updateAnswerNumber(@RequestBody Map<String, String> mapParams) {
        String uuid = mapParams.get("uuid");
        userQuestionService.updateAnswerNumber(uuid);
        int ret = userQuestionService.selectAnswerNumber(uuid);
        return Result.success(ret);
    }

    /**
     * 复制问卷
     */
    @RequestMapping("/copy")
    public Result copyUserQuestion(@RequestBody Map<String, String> mapParams) {
        String uuid = mapParams.get("uuid");
        UserQuestion userQuestion = userQuestionService.getUserQuestionByuuId(uuid);
        UUID longuuid = UUID.randomUUID();
        String newUuid = longuuid.toString().substring(0, 8);
        // 复制问卷外面的部分（title，content）
        UserQuestion newUserQuestion = new UserQuestion();
        newUserQuestion.setUuid(newUuid);
        newUserQuestion.setUsername(UserJwt.getUsername());
        newUserQuestion.setTitle(userQuestion.getTitle());
        newUserQuestion.setContent(userQuestion.getContent());
        newUserQuestion.setIsPublish(0);
        userQuestionService.insertUserQuestion(newUserQuestion);

        // 复制问卷里面的部分
        // 根据uuid查询问卷
        List<SingleQuestionModel> singleQuestionModels = questionService.getAllQuestions(uuid);
        for (SingleQuestionModel singleQuestionModel : singleQuestionModels) {
            UUID longuuid1 = UUID.randomUUID();
            String id = longuuid1.toString().substring(0, 8);
            singleQuestionModel.setId(id);
            singleQuestionModel.setUserquestionid(newUuid);
            questionService.addQuestion(singleQuestionModel);
        }
        return Result.success("复制成功");
    }
}