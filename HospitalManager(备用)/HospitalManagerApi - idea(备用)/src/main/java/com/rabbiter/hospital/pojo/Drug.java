//这是一个Java类文件，定义了一个名为Drug的POJO类。它表示药品的信息，并与数据库表"drug"对应。
//
//        该类的属性包括：
//
//        drId：药品ID（数据库字段名为"dr_id"，对应Excel中的"药品ID"）
//        drName：药品名称（对应Excel中的"药品名称"）
//        drPrice：药品价格（对应Excel中的"药品价格"）
//        drNumber：药品数量（对应Excel中的"药品数量"）
//        drUnit：药品单位（对应Excel中的"药品单位"）
//        drPublisher：药品生产商（对应Excel中的"药品生产商"）
//        该类提供了默认构造函数和一个带有参数的构造函数，用于创建对象。
//
//        该类还提供了getter和setter方法，并重写了toString()方法。

package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("drug")
public class Drug {
    @TableId(value = "dr_id")
    @JsonProperty("drId")
    private int drId;
    @JsonProperty("drName")
    private String drName;
    @JsonProperty("drPrice")
    private double drPrice;
    @JsonProperty("drNumber")
    private int drNumber;
    @JsonProperty("drUnit")
    private String drUnit;
    @JsonProperty("drPublisher")
    private String drPublisher;

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
