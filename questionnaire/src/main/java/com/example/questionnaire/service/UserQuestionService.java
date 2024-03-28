package com.example.questionnaire.service;

import com.example.questionnaire.mapper.UserLogMapper;
import com.example.questionnaire.mapper.UserQuestionMapper;
import com.example.questionnaire.model.UserQuestion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * 用户问卷服务实现类
 */
@Service
@Slf4j
public class UserQuestionService {

    @Autowired
    private UserQuestionMapper userQuestionMapper;

    @Autowired
    private UserLogMapper userLogMapper;

    /**
     * 获取所有用户问卷
     */
    public List<UserQuestion> getAllUserQuestions() {
        return userQuestionMapper.getAllUserQuestions();
    }

    /**
     * 查询所有人已发布的问卷
     */
    public List<UserQuestion> getAllUserQuestionsPublish() {
        return userQuestionMapper.getAllUserQuestionsPublish();
    }


    /**
     * 根据问卷uuid查询问卷
     * @param uuid 问卷id
     * @return 问卷对象
     */
    public UserQuestion getUserQuestionByuuId(String uuid) {
        return userQuestionMapper.getUserQuestionByuuId(uuid);
    }

    /**
     * 根据用户名查询问卷
     * @param username 用户名
     * @return 用户问卷列表
     */
    public List<UserQuestion> getUserQuestionsByUsername(String username) {
        return userQuestionMapper.getUserQuestionsByUsername(username);
    }

    /**
     * 插入新的用户问卷
     * @param userQuestion 用户问卷对象
     * @return 插入的用户问卷id
     */
    public String insertUserQuestion(UserQuestion userQuestion) {
        UUID longuuid = UUID.randomUUID();
        String uuid = longuuid.toString().substring(0, 8);
        userQuestion.setUuid(uuid);
        log.info("插入新的用户问卷:{}", userQuestion);
        userQuestionMapper.insertUserQuestion(userQuestion);
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 使用格式化输出当前日期和时间，带上年月日时分秒关键词
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日的HH时mm分ss秒");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        String content = "您于" + formattedDateTime + "发布了问卷《" + userQuestion.getTitle() + "》"+", 该问卷uuid为"+userQuestion.getUuid();
        userLogMapper.insertUserLog(userQuestion.getUsername(), 1, content);
        return userQuestion.getUuid();
    }

    /**
     * 更新用户问卷
     * @param userQuestion 用户问卷对象
     */
    public void updateUserQuestion(UserQuestion userQuestion) {
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 使用格式化输出当前日期和时间，带上年月日时分秒关键词
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日的HH时mm分ss秒");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        String content = "您于" + formattedDateTime + "修改了问卷《" + userQuestion.getTitle() + "》"+", 该问卷uuid为"+userQuestion.getUuid();
        userLogMapper.insertUserLog(userQuestion.getUsername(), 2, content);
        userQuestionMapper.updateUserQuestion(userQuestion);
    }

    /**
     * 删除用户问卷
     * @param uuid 用户问卷id
     */
    public void deleteUserQuestion(String uuid) {
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 使用格式化输出当前日期和时间，带上年月日时分秒关键词
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日的HH时mm分ss秒");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        // 获取问卷信息
        UserQuestion userQuestion = userQuestionMapper.getUserQuestionByuuId(uuid);
        String content = "您于" + formattedDateTime + "删除了问卷《" + userQuestion.getTitle() + "》"+", 该问卷uuid为"+userQuestion.getUuid();
        userLogMapper.insertUserLog(userQuestion.getUsername(), 3, content);
        userQuestionMapper.deleteUserQuestion(uuid);
    }

    // 删除该用户全部问卷
    public int deleteAllUserQuestions(String username) {
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 使用格式化输出当前日期和时间，带上年月日时分秒关键词
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日的HH时mm分ss秒");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        // 查询该用户所有问卷
        List<UserQuestion> userQuestions = userQuestionMapper.getUserQuestionsByUsername(username);
        for (UserQuestion userQuestion : userQuestions) {
            String content = "您于" + formattedDateTime + "删除了问卷《" + userQuestion.getTitle() + "》"+", 该问卷uuid为"+userQuestion.getUuid();
            userLogMapper.insertUserLog(userQuestion.getUsername(), 3, content);
        }
        return userQuestionMapper.deleteUserQuestionsByUsername(username);
    }

    // 查询该用户所有发布的问卷
    public List<UserQuestion> getUserQuestionsPublish(String username) {
        return userQuestionMapper.getUserQuestionsPublish(username);
    }

    // 查询该用户所有未发布的问卷
    public List<UserQuestion> getUserQuestionsNoPublish(String username) {
        return userQuestionMapper.getUserQuestionsNoPublish(username);
    }

    // 修改问卷发布状态
    public void updatePublishStatus(String uuid, int isPublish) {
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 使用格式化输出当前日期和时间，带上年月日时分秒关键词
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日的HH时mm分ss秒");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        // 获取问卷信息
        UserQuestion userQuestion = userQuestionMapper.getUserQuestionByuuId(uuid);
        String content = "您于" + formattedDateTime + "修改了问卷《" + userQuestion.getTitle() + "》"+", 该问卷uuid为"+userQuestion.getUuid();
        userLogMapper.insertUserLog(userQuestion.getUsername(), 2, content);
        userQuestionMapper.updatePublishStatus(uuid, isPublish);
    }

    // 问卷回答数加一
    public int updateAnswerNumber(String uuid) {
        return userQuestionMapper.updateAnswerNumber(uuid);
    }

    // 查询问卷回答数
    public int selectAnswerNumber(String uuid) {
        return userQuestionMapper.selectAnswerNumber(uuid);
    }

    // 根据问卷uuid查作者
    public String getUsernameByUuid(String uuid) {
        return userQuestionMapper.getUsernameByUuid(uuid);
    }
}
