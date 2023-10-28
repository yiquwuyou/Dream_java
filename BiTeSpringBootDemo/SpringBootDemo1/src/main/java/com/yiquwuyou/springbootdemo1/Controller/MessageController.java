package com.yiquwuyou.springbootdemo1.Controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/message")
@RestController
public class MessageController {

    private List<MessageInfo> messageInfos = new ArrayList<>();
    @RequestMapping("/publish")
    public Boolean publishMessage(MessageInfo messageInfo){
        // 进行参数的校验
        if(!StringUtils.hasLength(messageInfo.getFrom())
        || !StringUtils.hasLength(messageInfo.getTo())
        || !StringUtils.hasLength(messageInfo.getMessage())){
            return false;
        }

        // 添加留言
        messageInfos.add(messageInfo);
        return true;
    }

    @RequestMapping("/getMessageInfo")
    public List<MessageInfo> getMessageInfo(){

        return messageInfos;
    }
}
