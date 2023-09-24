//这是一个药品映射器类。
//
//        以下是这个映射器类的详细解释：
//
//        DrugMapper - 这是一个接口，继承自BaseMapper接口，并指定了泛型类型为Drug，表示该映射器用于操作Drug实体类对应的数据库表。
//        extends BaseMapper<Drug> - 使用extends关键字继承BaseMapper接口，并指定了泛型类型为Drug，表示该映射器对Drug实体类具有基本的数据库操作方法。
//        BaseMapper<Drug> - BaseMapper是MyBatis-Plus提供的一个通用映射器接口，包含了常用的数据库操作方法，如增删改查等。
//        <Drug> - 尖括号中的Drug表示该映射器操作的是Drug实体类对应的数据库表。

package com.rabbiter.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.hospital.pojo.Drug;

public interface DrugMapper extends BaseMapper<Drug> {
}
