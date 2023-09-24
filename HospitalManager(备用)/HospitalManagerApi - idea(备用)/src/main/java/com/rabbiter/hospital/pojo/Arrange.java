//这是一个名为Arrange的POJO类，用于表示医院的排班信息。
//
//        以下是该类的详细解释：
//
//@TableName(“arrange”) - 该注解表示该类对应数据库中的"arrange"表。
//
//@TableId(value = “ar_id”) - 该注解表示arId字段是表中的主键。
//
//@JsonProperty(“arId”) - 该注解表示arId字段在JSON序列化和反序列化时使用"arId"作为属性名。
//
//private String arId - 表示排班信息的ID。
//
//private String arTime - 表示排班时间。
//
//private int dId - 表示与该排班信息关联的医生ID。
//
//@TableField(exist = false) - 该注解表示doctor字段不映射到数据库表中的列。
//
//private Doctor doctor - 表示与该排班信息关联的医生实体。
//
//        构造函数和getter/setter方法 - 提供了对这些私有字段的访问和修改。
//
//        toString() 方法 - 用于将对象转换为字符串表示，方便输出对象的详细信息。

package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("arrange")
public class Arrange {
    @TableId(value = "ar_id")
    @JsonProperty("arId")
    private String arId;
    @JsonProperty("arTime")
    private String arTime;
    @JsonProperty("dId")
    private int dId;
    /**
     * 多表查询用
     */
    @TableField(exist = false)
    private Doctor doctor;

    public Arrange() {
    }

    public Arrange(String arId, String arTime, int dId, Doctor doctor) {
        this.arId = arId;
        this.arTime = arTime;
        this.dId = dId;
        this.doctor = doctor;
    }

    public String getArId() {
        return arId;
    }

    public void setArId(String arId) {
        this.arId = arId;
    }

    public String getArTime() {
        return arTime;
    }

    public void setArTime(String arTime) {
        this.arTime = arTime;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Arrange{" +
                "arId='" + arId + '\'' +
                ", arTime='" + arTime + '\'' +
                ", dId=" + dId +
                ", doctor=" + doctor +
                '}';
    }
}
