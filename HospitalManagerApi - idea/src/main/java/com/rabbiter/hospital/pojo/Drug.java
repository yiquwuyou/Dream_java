package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

// 药品信息
@TableName("drug")
public class Drug {
    @TableId(value = "dr_id")
    @JsonProperty("drId")
    private int drId;            // 药品ID
    @JsonProperty("drName")
    private String drName;       // 药品名称
    @JsonProperty("drPrice")
    private double drPrice;       // 药品价格
    @JsonProperty("drNumber")
    private int drNumber;         // 药品数量
    @JsonProperty("drUnit")
    private String drUnit;        // 药品单位（比如：盒、包）
    @JsonProperty("drPublisher")
    private String drPublisher;      // 药品生产商

    public Drug() {
    }

    public Drug(int drId, String drName, double drPrice, int drNumber, String drUnit, String drPublisher) {
        this.drId = drId;
        this.drName = drName;
        this.drPrice = drPrice;
        this.drNumber = drNumber;
        this.drUnit = drUnit;
        this.drPublisher = drPublisher;
    }

    public int getDrId() {
        return drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public double getDrPrice() {
        return drPrice;
    }

    public void setDrPrice(double drPrice) {
        this.drPrice = drPrice;
    }

    public int getDrNumber() {
        return drNumber;
    }

    public void setDrNumber(int drNumber) {
        this.drNumber = drNumber;
    }

    public String getDrUnit() {
        return drUnit;
    }

    public void setDrUnit(String drUnit) {
        this.drUnit = drUnit;
    }

    public String getDrPublisher() {
        return drPublisher;
    }

    public void setDrPublisher(String drPublisher) {
        this.drPublisher = drPublisher;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drId=" + drId +
                ", drName='" + drName + '\'' +
                ", drPrice=" + drPrice +
                ", drNumber=" + drNumber +
                ", drUnit='" + drUnit + '\'' +
                ", drPublisher='" + drPublisher + '\'' +
                '}';
    }
}
