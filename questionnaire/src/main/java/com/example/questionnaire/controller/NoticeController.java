package com.example.questionnaire.controller;

import java.util.Map;
import java.util.UUID;

import com.example.questionnaire.model.Notice;
import com.example.questionnaire.model.Result;
import com.example.questionnaire.model.UserJwt;
import com.example.questionnaire.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
@CrossOrigin(origins="*")
@Slf4j
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // 查询系统公告
    @RequestMapping("/select")
    public Result selectNotice() {
        return Result.success(noticeService.selectNotice());
    }

    // 添加系统公告
    @RequestMapping("/insert")
    public Result insertNotice(@RequestBody Map<String, String> mapParams) {
        log.info(UserJwt.getUsername());
        if (!UserJwt.getUsername().equals("admin1")) {
            return Result.fail("无权限");
        }
        UUID longuuid = UUID.randomUUID();
        String uuid = longuuid.toString().substring(0, 8);
        String title = mapParams.get("title");
        String content = mapParams.get("content");
        noticeService.insertNotice(uuid, title, content);
        return Result.success("添加成功");
    }


    // 删除系统公告
    @RequestMapping("/delete")
    public Result deleteNotice(@RequestBody Map<String, String> mapParams) {
        String uuid = mapParams.get("uuid");
        noticeService.deleteNotice(uuid);
        return Result.success("删除成功");
    }

    // 查看最后一条公告
    @RequestMapping("/selectLast")
    public Result selectLastNotice() {
        return Result.success(noticeService.selectLastNotice());
    }
}

