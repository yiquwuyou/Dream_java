package com.example.questionnaire.utils;

import com.example.questionnaire.model.Option;
import com.example.questionnaire.model.Question;
import com.example.questionnaire.model.SingleQuestionModel;
import com.example.questionnaire.model.WriteQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionWriteConverter {
    public static Question convert(WriteQuestion writeQuestion) {
        Question question = new Question();
        question.setId(writeQuestion.getUuid());
        question.setUserquestionid(writeQuestion.getUserquestionid());
        question.setTitle(writeQuestion.getTitle());
        question.setType(writeQuestion.getType());
        question.setSort(writeQuestion.getSort());
        question.setRequired(writeQuestion.getIsRequired() == 1); // 转换int为boolean
        question.setContent(writeQuestion.getContent());
        question.setPlaceholder(writeQuestion.getPlaceholder());
        question.setRadio(writeQuestion.getRadio());

        // 转换String为List<Option>
        question.setRadioOptions(convertStringToOptionList(writeQuestion.getRadioOptions()));
        question.setCheckboxOptions(convertStringToOptionList(writeQuestion.getCheckboxOptions()));

        // 转换String为List<String>
        question.setCheckList(convertStringToStringList(writeQuestion.getCheckList()));

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
