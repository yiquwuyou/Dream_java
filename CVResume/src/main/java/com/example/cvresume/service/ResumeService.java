package com.example.cvresume.service;

import com.example.cvresume.mapper.ResumeMapper;
import com.example.cvresume.model.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeService {

    @Autowired
    private ResumeMapper resumeMapper;

    // 上传到简历库
    public Integer uploadResume(Resume resume){
        return resumeMapper.insertResume(resume);
    }

    // 查询简历库某一条数据
    public Resume selectByUserName(Integer id){
        return resumeMapper.selectByUserName(id);
    }

    // 查询简历库所有数据
    public List<Resume> selectAll(){
        return resumeMapper.selectAll();
    }
}
