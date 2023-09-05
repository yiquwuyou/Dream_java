package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class ceshi {
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            // 获取上传的图片文件名
            String originalFilename = image.getOriginalFilename();

            // 设置存储目录的路径
            String uploadDirectory = "D:\\code_github\\";

            // 创建存储目录（如果不存在）
            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // 构建目标文件对象
            File destination = new File(uploadDirectory + originalFilename);

            // 保存上传的图片文件
            image.transferTo(destination);

            return "Image uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Image upload failed.";
        }
    }


}
