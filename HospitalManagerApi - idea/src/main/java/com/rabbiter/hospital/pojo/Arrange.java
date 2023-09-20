package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

// 表示医院的排班信息
@TableName("arrange")         // 表示该类对应数据库中的 “arrange” 表
public class Arrange {
    @TableId(value = "ar_id")     // 表示 arId 是表中的主键
    @JsonProperty("arId")         // 相当于给 arId 起了个 括号内的别名，供 JSON 调用时使用
    private String arId;
    @JsonProperty("arTime")
    private String arTime;        // 表示排班时间
    @JsonProperty("dId")
    private int dId;              // 表示与排班信息关联的医生ID（一般保存的信息与 Id 一致）
    /**
     * 多表查询用
     */
    @TableField(exist = false)
    private Doctor doctor;        // 表示与排班信息关联的医生实体

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
