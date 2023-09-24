//这是一个排班映射器类。
//
//        以下是这个映射器类的详细解释：
//
//        ArrangeMapper - 这是一个接口，继承自BaseMapper接口，并指定了泛型类型为Arrange，表示该映射器用于操作Arrange实体类对应的数据库表。
//        extends BaseMapper<Arrange> - 使用extends关键字继承BaseMapper接口，并指定了泛型类型为Arrange，表示该映射器对Arrange实体类具有基本的数据库操作方法。
//        BaseMapper<Arrange> - BaseMapper是MyBatis-Plus提供的一个通用映射器接口，包含了常用的数据库操作方法，如增删改查等。
//<Arrange> - 尖括号中的Arrange表示该映射器操作的是Arrange实体类对应的数据库表。
//        findByTime() 方法 - 这是一个自定义的查询方法，根据日期和科室查询排班信息。
//@Param(“ar_time”) - 使用@Param注解标记参数，指定参数名为ar_time，用于与SQL语句中的参数进行对应。
//@Param(“d_section”) - 使用@Param注解标记参数，指定参数名为d_section，用于与SQL语句中的参数进行对应。
//        List<Arrange> - 返回一个包含Arrange对象的列表，表示查询结果。

package com.rabbiter.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.hospital.pojo.Arrange;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArrangeMapper extends BaseMapper<Arrange> {

    /**
     * 根据日期查询排班信息
     */
    List<Arrange> findByTime(@Param("ar_time") String arTime, @Param("d_section") String dSection);

}
