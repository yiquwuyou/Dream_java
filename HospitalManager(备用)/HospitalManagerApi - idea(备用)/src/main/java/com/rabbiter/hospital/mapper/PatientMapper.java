//这是一个患者映射器类。
//
//        以下是这个映射器类的详细解释：
//
//        PatientMapper - 这是一个接口，继承自BaseMapper接口，并指定了泛型类型为Patient，表示该映射器用于操作Patient实体类对应的数据库表。
//        extends BaseMapper<Patient> - 使用extends关键字继承BaseMapper接口，并指定了泛型类型为Patient，表示该映射器对Patient实体类具有基本的数据库操作方法。
//        BaseMapper<Patient> - BaseMapper是MyBatis-Plus提供的一个通用映射器接口，包含了常用的数据库操作方法，如增删改查等。
//        <Patient> - 尖括号中的Patient表示该映射器操作的是Patient实体类对应的数据库表。
//        patientAge() 方法 - 这是一个自定义的统计方法，用于统计患者的年龄段人数。
//        int startAge - 参数startAge表示起始年龄，用于筛选符合起始年龄的患者。
//        int endAge - 参数endAge表示结束年龄，用于筛选符合结束年龄的患者。
//        Integer - 返回一个整数，表示统计结果。

package com.rabbiter.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.hospital.pojo.Patient;
import org.apache.ibatis.annotations.Param;

public interface PatientMapper extends BaseMapper<Patient> {
    /**
     * 统计患者男女人数
     */
    Integer patientAge(@Param("startAge") int startAge, @Param("endAge") int endAge);
}
