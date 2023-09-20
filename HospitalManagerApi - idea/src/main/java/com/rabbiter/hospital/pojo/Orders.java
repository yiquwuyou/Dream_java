package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

// 医生给患者开的处方单（包含患者做过的项目，买的药等等）
@TableName("orders")
public class Orders {
    @TableId(value = "o_id")
    @JsonProperty("oId")
    private int oId;          // 订单ID
    @JsonProperty("pId")
    private int pId;          // 患者ID
    @JsonProperty("dId")
    private int dId;          // 医生ID
    @JsonProperty("oRecord")
    private String oRecord;      // 订单记录
    @JsonProperty("oStart")
    private String oStart;       // 订单开始时间
    @JsonProperty("oEnd")
    private String oEnd;         // 订单结束时间
    @JsonProperty("oState")
    private Integer oState;      // 订单状态
    @JsonProperty("oDrug")
    private String oDrug;       // 药物信息
    @JsonProperty("oCheck")
    private String oCheck;       // 检查信息（如核磁共振等）
    @JsonProperty("oTotalPrice")
    private Double oTotalPrice;      // 订单总价格
    @JsonProperty("oPriceState")
    private Integer oPriceState;      // 价格状态（1代表已付，0代表未付）
    @JsonProperty("countGender")
    @TableField(exist = false)
    private Integer countGender;    // 性别统计（供统计表计算用）
    @JsonProperty("oAdvice")
    private String oAdvice;         // 订单建议
    //多表查询用
    @TableField(exist = false)//声明不是数据库里面的字段
    private Doctor doctor;         // 医生信息（多表查询用）
    //多表查询用
    @TableField(exist = false)//声明不是数据库里面的字段
    private Patient patient;       // 患者信息（多表查询用）
    @TableField(exist = false)
    private Integer countSection;      // 科室统计
    @JsonProperty("dName")
    @TableField(exist = false)
    private String dName;              // 医生姓名
    @JsonProperty("pName")
    @TableField(exist = false)
    private String pName;               // 患者姓名

    public Orders() {
    }

    public Orders(int oId, int pId, int dId, String oRecord, String oStart, String oEnd, Integer oState, String oDrug, String oCheck, Double oTotalPrice, Integer oPriceState, Integer countGender, String oAdvice, Doctor doctor, Patient patient, Integer countSection, String dName, String pName) {
        this.oId = oId;
        this.pId = pId;
        this.dId = dId;
        this.oRecord = oRecord;
        this.oStart = oStart;
        this.oEnd = oEnd;
        this.oState = oState;
        this.oDrug = oDrug;
        this.oCheck = oCheck;
        this.oTotalPrice = oTotalPrice;
        this.oPriceState = oPriceState;
        this.countGender = countGender;
        this.oAdvice = oAdvice;
        this.doctor = doctor;
        this.patient = patient;
        this.countSection = countSection;
        this.dName = dName;
        this.pName = pName;
    }

    public int getOId() {
        return oId;
    }

    public void setOId(int oId) {
        this.oId = oId;
    }

    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getORecord() {
        return oRecord;
    }

    public void setORecord(String oRecord) {
        this.oRecord = oRecord;
    }

    public String getOStart() {
        return oStart;
    }

    public void setOStart(String oStart) {
        this.oStart = oStart;
    }

    public String getOEnd() {
        return oEnd;
    }

    public void setOEnd(String oEnd) {
        this.oEnd = oEnd;
    }

    public Integer getOState() {
        return oState;
    }

    public void setOState(Integer oState) {
        this.oState = oState;
    }

    public String getODrug() {
        return oDrug;
    }

    public void setODrug(String oDrug) {
        this.oDrug = oDrug;
    }

    public String getOCheck() {
        return oCheck;
    }

    public void setOCheck(String oCheck) {
        this.oCheck = oCheck;
    }

    public Double getOTotalPrice() {
        return oTotalPrice;
    }

    public void setOTotalPrice(Double oTotalPrice) {
        this.oTotalPrice = oTotalPrice;
    }

    public Integer getOPriceState() {
        return oPriceState;
    }

    public void setOPriceState(Integer oPriceState) {
        this.oPriceState = oPriceState;
    }

    public Integer getCountGender() {
        return countGender;
    }

    public void setCountGender(Integer countGender) {
        this.countGender = countGender;
    }

    public String getOAdvice() {
        return oAdvice;
    }

    public void setOAdvice(String oAdvice) {
        this.oAdvice = oAdvice;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getCountSection() {
        return countSection;
    }

    public void setCountSection(Integer countSection) {
        this.countSection = countSection;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oId=" + oId +
                ", pId=" + pId +
                ", dId=" + dId +
                ", oRecord='" + oRecord + '\'' +
                ", oStart='" + oStart + '\'' +
                ", oEnd='" + oEnd + '\'' +
                ", oState=" + oState +
                ", oDrug='" + oDrug + '\'' +
                ", oCheck='" + oCheck + '\'' +
                ", oTotalPrice=" + oTotalPrice +
                ", oPriceState=" + oPriceState +
                ", countGender=" + countGender +
                ", oAdvice='" + oAdvice + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", countSection=" + countSection +
                ", dName='" + dName + '\'' +
                ", pName='" + pName + '\'' +
                '}';
    }
}
