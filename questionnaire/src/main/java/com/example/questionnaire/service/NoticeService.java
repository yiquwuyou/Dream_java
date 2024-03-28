package com.example.questionnaire.service;

import com.example.questionnaire.mapper.NoticeMapper;
import com.example.questionnaire.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    // 查询所有系统公告
    public List<Notice> selectNotice() {
        return noticeMapper.selectNotice();
    }

    // 添加系统公告
    public void insertNotice(String uuid, String title, String content) {
        noticeMapper.insertNotice(uuid, title, content);
    }

    // 删除系统公告
    public void deleteNotice(String uuid) {
        noticeMapper.deleteNotice(uuid);
    }

    // 查看最后一条公告
    public Notice selectLastNotice() {
        return noticeMapper.selectLastNotice();
    }
}
