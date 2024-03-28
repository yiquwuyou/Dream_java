package com.example.questionnaire.utils;

import com.example.questionnaire.model.Option;
import com.example.questionnaire.model.Question;
import com.example.questionnaire.model.SingleQuestionModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionConverter {

    public static Question convert(SingleQuestionModel singleQuestionModel) {
        Question question = new Question();
        question.setId(singleQuestionModel.getId());
        question.setTitle(singleQuestionModel.getTitle());
        question.setType(singleQuestionModel.getType());
        question.setSort(singleQuestionModel.getSort());
        question.setRequired(singleQuestionModel.getIsRequired() == 1); // 转换int为boolean
        question.setContent(singleQuestionModel.getContent());
        question.setPlaceholder(singleQuestionModel.getPlaceholder());
        question.setRadio(singleQuestionModel.getRadio());

        // 转换String为List<Option>
        question.setRadioOptions(convertStringToOptionList(singleQuestionModel.getRadioOptions()));
        question.setCheckboxOptions(convertStringToOptionList(singleQuestionModel.getCheckboxOptions()));

        // 转换String为List<String>
        question.setCheckList(convertStringToStringList(singleQuestionModel.getCheckList()));

        return question;
    }

    private static List<Option> convertStringToOptionList(String optionsString) {
        List<Option> optionList = new ArrayList<>();
        if (optionsString != null && !optionsString.isEmpty()) {
            String[] options = optionsString.split(",");
            for (String option : options) {
                String[] parts = option.split(":");
                if (parts.length == 2) {
                    Option opt = new Option();
                    opt.setLabel(parts[0]);
                    opt.setDisabled(Boolean.parseBoolean(parts[1]));
                    optionList.add(opt);
                }
            }
        }
        return optionList;
    }

    private static List<String> convertStringToStringList(String stringListString) {
        List<String> stringList = new ArrayList<>();
        if (stringListString != null && !stringListString.isEmpty()) {
            String[] parts = stringListString.split(",");
            stringList.addAll(Arrays.asList(parts));
        }
        return stringList;
    }
}
