package com.example.cvresume.controller;

import com.example.cvresume.model.Result;
import com.example.cvresume.model.Resume;
import com.example.cvresume.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
@Slf4j
@CrossOrigin(origins="*")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;
    // 上传到简历库
    @RequestMapping("/upload")
    public Result uploadResume(@RequestBody Resume resume){
        log.info("上传到简历库:{}",resume);
        // 上传到简历库
        int ret = resumeService.uploadResume(resume);
        log.info("上传到简历库结果:{}",resume.getId());

        // 查询刚插入的整条简历数据返回给前端
        Resume resume1 = resumeService.selectByUserName(resume.getId());

        return Result.success(resume1);
    }

    // 查询简历库所有数据
    @RequestMapping("/selectAll")
    public Result selectAll(){
        log.info("查询简历库所有数据");
        return Result.success(resumeService.selectAll());
    }
}
