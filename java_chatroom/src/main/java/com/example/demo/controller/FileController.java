package com.example.demo.controller;
// 不属于聊天室内部代码，后序删除

import com.example.demo.model.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public Result upload(MultipartFile file){

        String originalFilename = file.getOriginalFilename();  // 文件的原始名称
        return Result.success(""); // 返回文件的链接，这个链接就是文件的下载地址，这个下载地址就是我的后台提供出来的

    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(new byte[1024]); // 数组是一个字节数组，也就是文件的字节流数组

    }
}
