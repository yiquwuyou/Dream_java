//这是一个病床映射器类。
//
//        以下是这个映射器类的详细解释：
//
//        BedMapper - 这是一个接口，继承自BaseMapper接口，并指定了泛型类型为Bed，表示该映射器用于操作Bed实体类对应的数据库表。
//        extends BaseMapper<Bed> - 使用extends关键字继承BaseMapper接口，并指定了泛型类型为Bed，表示该映射器对Bed实体类具有基本的数据库操作方法。
//        BaseMapper<Bed> - BaseMapper是MyBatis-Plus提供的一个通用映射器接口，包含了常用的数据库操作方法，如增删改查等。
//        <Bed> - 尖括号中的Bed表示该映射器操作的是Bed实体类对应的数据库表。
//        bedPeople() 方法 - 这是一个自定义的统计方法，用于统计今天的住院人数。
//        String bStart - 参数bStart表示住院开始日期，用于筛选今天入院的病人。
//        int - 返回一个整数，表示统计结果。

package com.rabbiter.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.hospital.pojo.Bed;

public interface BedMapper extends BaseMapper<Bed> {
    /**
     * 统计今天住院人数
     */
    int bedPeople(String bStart);
}
