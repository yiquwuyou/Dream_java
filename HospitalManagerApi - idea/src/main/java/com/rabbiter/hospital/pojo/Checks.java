package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

// 身体检查的项目，如 CT
@TableName(value = "checks")
public class Checks {
    @TableId(value = "ch_id")
    @JsonProperty("chId")
    private int chId;           // 表示检查项目的ID
    @JsonProperty("chName")
    private String chName;      // 表示检查项目的名称
    @JsonProperty("chPrice")
    private Double chPrice;     // 表示检查项目的价格

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
