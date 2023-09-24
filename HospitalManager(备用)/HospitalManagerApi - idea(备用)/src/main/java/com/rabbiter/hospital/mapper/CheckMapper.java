//这是一个检查映射器类。
//
//        以下是这个映射器类的详细解释：
//
//        CheckMapper - 这是一个接口，继承自BaseMapper接口，并指定了泛型类型为Checks，表示该映射器用于操作Checks实体类对应的数据库表。
//        extends BaseMapper<Checks> - 使用extends关键字继承BaseMapper接口，并指定了泛型类型为Checks，表示该映射器对Checks实体类具有基本的数据库操作方法。
//        BaseMapper<Checks> - BaseMapper是MyBatis-Plus提供的一个通用映射器接口，包含了常用的数据库操作方法，如增删改查等。
//        <Checks> - 尖括号中的Checks表示该映射器操作的是Checks实体类对应的数据库表。

package com.rabbiter.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.hospital.pojo.Checks;

public interface CheckMapper extends BaseMapper<Checks> {
}
