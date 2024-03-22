package com.example.questionnaire.mapper;

import com.example.questionnaire.model.Resume;
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

    // 获取当前页的信息
    @Select("select * from resume where delete_flag !=0 " +
            "order by id desc limit #{offset},#{pageSize}")
    List<Resume> selectResumeByPage(Integer offset, Integer pageSize);

    // 获取总记录数
    @Select("select count(1) from resume where delete_flag !=0")
    Integer count();
}
