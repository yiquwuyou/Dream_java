package com.example.questionnaire.utils;

import com.example.questionnaire.model.Option;
import com.example.questionnaire.model.Question;
import com.example.questionnaire.model.SingleQuestionModel;
import com.example.questionnaire.model.WriteQuestion;

import java.util.List;
import java.util.stream.Collectors;

public class WriteQuestionConverter {
    public static WriteQuestion convert(Question question, String uuid, String originusername,
                                        String writeusername, Integer isPublish) {
        WriteQuestion writeQuestion = new WriteQuestion();
        writeQuestion.setUuid(uuid);
        writeQuestion.setUserquestionid(question.getUserquestionid());
        writeQuestion.setTitle(question.getTitle());
        writeQuestion.setType(question.getType());
        writeQuestion.setOriginusername(originusername);
        writeQuestion.setWriteusername(writeusername);
        writeQuestion.setIsPublish(isPublish);
        writeQuestion.setSort(question.getSort());
        if(question.isRequired() == true){
            writeQuestion.setIsRequired(1);
        } else {
            writeQuestion.setIsRequired(0);
        }
        writeQuestion.setContent(question.getContent());
        writeQuestion.setPlaceholder(question.getPlaceholder());
        writeQuestion.setRadio(question.getRadio());

        // 转换List<Option>为String
        writeQuestion.setRadioOptions(convertOptionListToString(question.getRadioOptions()));
        writeQuestion.setCheckboxOptions(convertOptionListToString(question.getCheckboxOptions()));

        // 转换List<String>为String
        writeQuestion.setCheckList(convertStringListToString(question.getCheckList()));

        return writeQuestion;
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
