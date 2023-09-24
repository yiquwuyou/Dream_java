//这是一个订单映射器类。
//
//        以下是这个映射器类的详细解释：
//
//        OrderMapper - 这是一个接口，继承自BaseMapper接口，并指定了泛型类型为Orders，表示该映射器用于操作Orders实体类对应的数据库表。
//        extends BaseMapper<Orders> - 使用extends关键字继承BaseMapper接口，并指定了泛型类型为Orders，表示该映射器对Orders实体类具有基本的数据库操作方法。
//        BaseMapper<Orders> - BaseMapper是MyBatis-Plus提供的一个通用映射器接口，包含了常用的数据库操作方法，如增删改查等。
//        <Orders> - 尖括号中的Orders表示该映射器操作的是Orders实体类对应的数据库表。
//        orderPeople() 方法 - 这是一个自定义的统计方法，用于统计今天的挂号人数。
//        String oStart - 参数oStart表示挂号开始日期，用于筛选今天的挂号记录。
//        int - 返回一个整数，表示统计结果。
//        orderPeopleByDid() 方法 - 这是一个自定义的统计方法，用于统计今天某个医生的挂号人数。
//        String oStart - 参数oStart表示挂号开始日期，用于筛选今天的挂号记录。
//        int dId - 参数dId表示医生ID，用于筛选某个特定医生的挂号记录。
//        int - 返回一个整数，表示统计结果。
//        orderGender() 方法 - 这是一个自定义的统计方法，用于统计挂号男女人数。
//        List<String> - 返回一个包含String类型元素的列表，表示统计结果。
//        findOrderByOid() 方法 - 这是一个自定义的查询方法，用于根据挂号单号查询挂号记录。
//        int oId - 参数oId表示挂号单号，用于指定要查询的挂号记录。
//        Orders - 返回一个Orders对象，表示查询到的挂号记录。
//        updateOrderByAdd() 方法 - 这是一个自定义的更新方法，用于增加诊断及医生意见。
//        Orders order - 参数order表示包含诊断及医生意见的挂号对象。
//        Integer - 返回一个整数，表示更新操作的结果。
//        orderSection() 方法 - 这是一个自定义的统计方法，用于统计过去20天的挂号科室人数。
//        String startTime - 参数startTime表示开始日期，用于筛选过去20天内的挂号记录。
//        String endTime - 参数endTime表示结束日期，用于筛选过去20天内的挂号记录。
//        List<String> - 返回一个包含String类型元素的列表，表示统计结果。
//        findOrderByNull() 方法 - 这是一个自定义的查询方法，用于查看当天的挂号列表。
//        int dId - 参数dId表示医生ID，用于筛选某个特定医生的挂号记录。
//        String oStart - 参数oStart表示当天的日期，用于筛选当天的挂号记录。
//        List<Orders> - 返回一个包含Orders对象的列表，表示查询到的挂号记录。
//        findOrderByPid() 方法 - 这是一个自定义的查询方法，用于根据患者ID查询挂号记录。
//        int pId - 参数pId表示患者ID，用于指定要查询的挂号记录。
//        List<Orders> - 返回一个包含Orders对象的列表，表示查询到的挂号记录。

package com.rabbiter.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.hospital.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<Orders> {
    /**
     * 统计今天挂号人数
     */
    int orderPeople(String oStart);
    /**
     * 统计今天某个医生挂号人数
     */
    int orderPeopleByDid(@Param("o_start") String oStart, @Param("d_id") int dId);
    /**
     * 统计挂号男女人数
     */
    List<String> orderGender();
    /**
     * 根据挂号单号查询挂号
     */
    Orders findOrderByOid(int oId);
    /**
     * 增加诊断及医生意见
     */
    Integer updateOrderByAdd(Orders order);
    /**
     * 统计过去20天挂号科室人数
     */
    List<String> orderSection(@Param("startTime") String startTime, @Param("endTime") String endTime);
    /**
     * 查看当天挂号列表
     */
    List<Orders> findOrderByNull(@Param("dId") int dId, @Param("oStart") String oStart);
    /**
     * 根据pId查询挂号
     */
    List<Orders> findOrderByPid(int pId);

}
