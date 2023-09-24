//这是一个名为Checks的POJO类，用于表示医院的检查项目信息。
//
//        以下是该类的详细解释：
//
//@TableName(“checks”) - 该注解表示该类对应数据库中的"checks"表。
//
//@TableId(value = “ch_id”) - 该注解表示chId字段是表中的主键。
//
//@JsonProperty(“chId”) - 该注解表示chId字段在JSON序列化和反序列化时使用"chId"作为属性名。
//
//private int chId - 表示检查项目的ID。
//
//private String chName - 表示检查项目的名称。
//
//private Double chPrice - 表示检查项目的价格。
//
//        构造函数和getter/setter方法 - 提供了对这些私有字段的访问和修改。
//
//        toString() 方法 - 用于将对象转换为字符串表示，方便输出对象的详细信息。

package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName(value = "checks")
public class Checks {
    @TableId(value = "ch_id")
    @JsonProperty("chId")
    private int chId;
    @JsonProperty("chName")
    private String chName;
    @JsonProperty("chPrice")
    private Double chPrice;

    public Checks() {
    }

    public Checks(int chId, String chName, Double chPrice) {
        this.chId = chId;
        this.chName = chName;
        this.chPrice = chPrice;
    }

    public int getChId() {
        return chId;
    }

    public void setChId(int chId) {
        this.chId = chId;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public Double getChPrice() {
        return chPrice;
    }

    public void setChPrice(Double chPrice) {
        this.chPrice = chPrice;
    }

    @Override
    public String toString() {
        return "Checks{" +
                "chId=" + chId +
                ", chName='" + chName + '\'' +
                ", chPrice=" + chPrice +
                '}';
    }
}
