package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

// 病人信息
@TableName("patient")
public class Patient {
    @TableId(value = "p_id")
    @JsonProperty("pId")
    private int pId;       // 病人ID
    @JsonProperty("pPassword")
    //@TableField(select = false)
    private String pPassword;     // 密码
    @JsonProperty("pName")
    private String pName;          // 患者姓名
    @JsonProperty("pGender")
    private String pGender;        // 患者性别
    @JsonProperty("pCard")
    private String pCard;          // 患者身份证号
    @JsonProperty("pEmail")
    private String pEmail;         // 患者邮箱
    @JsonProperty("pPhone")
    private String pPhone;         // 患者手机号
    @JsonProperty("pState")
    private Integer pState;       // 患者的状态信息
    @JsonProperty("pBirthday")
    private String pBirthday;       // 患者的生日日期
    @JsonProperty("pAge")
    private Integer pAge;           // 患者的年龄

    public Patient() {
    }

    public Patient(int pId, String pPassword, String pName, String pGender, String pCard, String pEmail, String pPhone, Integer pState, String pBirthday, Integer pAge) {
        this.pId = pId;
        this.pPassword = pPassword;
        this.pName = pName;
        this.pGender = pGender;
        this.pCard = pCard;
        this.pEmail = pEmail;
        this.pPhone = pPhone;
        this.pState = pState;
        this.pBirthday = pBirthday;
        this.pAge = pAge;
    }

    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public String getPPassword() {
        return pPassword;
    }

    public void setPPassword(String pPassword) {
        this.pPassword = pPassword;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public String getPGender() {
        return pGender;
    }

    public void setPGender(String pGender) {
        this.pGender = pGender;
    }

    public String getPCard() {
        return pCard;
    }

    public void setPCard(String pCard) {
        this.pCard = pCard;
    }

    public String getPEmail() {
        return pEmail;
    }

    public void setPEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getPPhone() {
        return pPhone;
    }

    public void setPPhone(String pPhone) {
        this.pPhone = pPhone;
    }

    public Integer getPState() {
        return pState;
    }

    public void setPState(Integer pState) {
        this.pState = pState;
    }

    public String getPBirthday() {
        return pBirthday;
    }

    public void setPBirthday(String pBirthday) {
        this.pBirthday = pBirthday;
    }

    public Integer getPAge() {
        return pAge;
    }

    public void setPAge(Integer pAge) {
        this.pAge = pAge;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "pId=" + pId +
                ", pPassword='" + pPassword + '\'' +
                ", pName='" + pName + '\'' +
                ", pGender='" + pGender + '\'' +
                ", pCard='" + pCard + '\'' +
                ", pEmail='" + pEmail + '\'' +
                ", pPhone='" + pPhone + '\'' +
                ", pState=" + pState +
                ", pBirthday='" + pBirthday + '\'' +
                ", pAge=" + pAge +
                '}';
    }
}
