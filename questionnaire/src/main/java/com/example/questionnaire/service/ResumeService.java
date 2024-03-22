package com.example.questionnaire.service;

import com.example.questionnaire.mapper.ResumeMapper;
import com.example.questionnaire.model.PageRequest;
import com.example.questionnaire.model.PageResult;
import com.example.questionnaire.model.Resume;
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

    // 获取当前页的信息
    public PageResult<Resume> selectResumeByPage(PageRequest pageRequest){
        return new PageResult<>(resumeMapper.selectResumeByPage(pageRequest.getOffset(),
                pageRequest.getPageSize()), resumeMapper.count(), pageRequest);
    }

    // 获取总记录数
    public Integer count(){
        return resumeMapper.count();
    }
}
