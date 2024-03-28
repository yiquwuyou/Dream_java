package com.example.questionnaire.model;

import lombok.Data;

@Data
public class SingleQuestionModel {
    private String id;        //唯一uuid
    private String userquestionid;   // 用户问卷id
    private String username;  // 用户名
    private String title;
    private String type;
    private Integer sort;
    private Integer isRequired;
    private String content;
    private String placeholder;
    private String radio;
    private String radioOptions;
    private String checkList;
    private String checkboxOptions;

}