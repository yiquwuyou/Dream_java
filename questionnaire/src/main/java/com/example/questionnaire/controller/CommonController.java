package com.example.questionnaire.controller;


import com.example.questionnaire.config.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */
@CrossOrigin(origins="*")
@RestController
@Slf4j
@RequestMapping("/file")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     * @param file
     * @return.
     *
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file){
        log.info("文件上传：{}",file);

        if (file == null || file.isEmpty()) {
            log.info("文件上传为空，无法进行后续处理！！！");
            return "文件为空";
        }
        try {
            // 原始文件名
            String originalFilename = file.getOriginalFilename();
            // 截取原始文件名的后缀   .png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;

            // 文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return filePath;
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }
        return null;
    }
}