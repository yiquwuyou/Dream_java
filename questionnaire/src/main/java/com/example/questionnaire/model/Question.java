package com.example.questionnaire.model;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String id;
    private String userquestionid;
    private String title;
    private String type;
    private Integer sort;
    private boolean isRequired;
    private String content;
    private String placeholder;
    private String radio;
    private List<Option> radioOptions;
    private List<String> checkList;
    private List<Option> checkboxOptions;

}



