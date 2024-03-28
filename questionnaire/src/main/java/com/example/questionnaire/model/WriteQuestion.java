package com.example.questionnaire.model;

import lombok.Data;

@Data
public class WriteQuestion {

    private String uuid;        //唯一uuid
    private String userquestionid;   // 用户问卷uuid
    private String originusername;  // 发布者用户名
    private String writeusername;  // 填写者用户名
    private Integer isPublish;  // 是否发布
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
