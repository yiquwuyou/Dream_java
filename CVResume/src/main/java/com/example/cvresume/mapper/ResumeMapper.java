package com.example.cvresume.mapper;

import com.example.cvresume.model.Resume;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ResumeMapper {

    // 插入

    Integer insertResume(Resume resume);
    // 查询
    @Select("select * from resume where id = #{id}")
    Resume selectByUserName(Integer id);

    // 查询简历库所有数据
    @Select("select * from resume")
    List<Resume> selectAll();
}
