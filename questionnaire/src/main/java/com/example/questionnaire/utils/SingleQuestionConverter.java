package com.example.questionnaire.utils;

import com.example.questionnaire.model.Option;
import com.example.questionnaire.model.Question;
import com.example.questionnaire.model.SingleQuestionModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SingleQuestionConverter {

    public static SingleQuestionModel convert(Question question, String id, String username) {
        SingleQuestionModel singleQuestionModel = new SingleQuestionModel();
        singleQuestionModel.setId(id);
        log.info("id:{}", id);
        singleQuestionModel.setUserquestionid(question.getUserquestionid());
        log.info("userquestionid:{}", question.getUserquestionid());
        singleQuestionModel.setTitle(question.getTitle());
        singleQuestionModel.setType(question.getType());
        singleQuestionModel.setUsername(username);
        singleQuestionModel.setSort(question.getSort());
        if(question.isRequired() == true){
            singleQuestionModel.setIsRequired(1);
        } else {
            singleQuestionModel.setIsRequired(0);
        }
        singleQuestionModel.setContent(question.getContent());
        singleQuestionModel.setPlaceholder(question.getPlaceholder());
        singleQuestionModel.setRadio(question.getRadio());

        // 转换List<Option>为String
        singleQuestionModel.setRadioOptions(convertOptionListToString(question.getRadioOptions()));
        singleQuestionModel.setCheckboxOptions(convertOptionListToString(question.getCheckboxOptions()));

        // 转换List<String>为String
        singleQuestionModel.setCheckList(convertStringListToString(question.getCheckList()));

        return singleQuestionModel;
    }

    private static String convertOptionListToString(List<Option> optionList) {
        if (optionList == null || optionList.isEmpty()) {
            return "";
        }
        return optionList.stream()
                .map(option -> option.getLabel() + ":" + option.isDisabled())
                .collect(Collectors.joining(","));
    }

    private static String convertStringListToString(List<String> stringList) {
        if (stringList == null || stringList.isEmpty()) {
            return "";
        }
        return String.join(",", stringList);
    }
}
