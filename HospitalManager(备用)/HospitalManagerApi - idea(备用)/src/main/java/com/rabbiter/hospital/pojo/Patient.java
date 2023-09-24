//这是一个名为Patient的Java类，用于建模医院管理系统中的患者信息。以下是该类的详细说明：
//
//        注解：该类使用了com.baomidou.mybatisplus.annotation和com.fasterxml.jackson.annotation包中的注解，用于定义数据库表名和JSON属性名称。
//
//@TableName("patient")：指定此类映射到名为"patient"的数据库表。
//@JsonProperty("pId")：在序列化期间，将属性pId映射到JSON属性名为"pId"。
//        字段：该类具有多个字段，用于表示患者信息。每个字段都有对应的getter和setter方法。
//
//        pId：表示患者的ID。
//        pPassword：表示患者的密码（注意：在生产系统中不建议以明文形式存储密码；考虑使用适当的密码哈希和安全实践）。
//        pName：表示患者的姓名。
//        pGender：表示患者的性别。
//        pCard：表示与患者关联的某种卡片。
//        pEmail：表示患者的电子邮件地址。
//        pPhone：表示患者的电话号码。
//        pState：表示患者的状态信息（可能是整数代码）。
//        pBirthday：表示患者的生日日期（以字符串形式）。
//        pAge：表示患者的年龄（以整数形式）。
//        构造方法：该类有两个构造方法，一个没有参数，另一个带有参数，用于初始化字段的值。这允许您创建Patient类的实例，可以选择提供或不提供其属性的初始值。
//
//        Getter和Setter方法：每个字段都有getter和setter方法，用于访问和修改字段的值。
//
//        toString方法：重写了toString方法，以提供Patient对象的可读字符串表示，这对于调试和记录目的可能很有用。
//
//        总的来说，该类提供了一个蓝图，用于在医院管理系统中创建和处理患者对象，其中的注解指示了如何将其映射到数据库表并序列化为JSON。

package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("patient")
public class Patient {
    @TableId(value = "p_id")
    @JsonProperty("pId")
    private int pId;
    @JsonProperty("pPassword")
    //@TableField(select = false)
    private String pPassword;
    @JsonProperty("pName")
    private String pName;
    @JsonProperty("pGender")
    private String pGender;
    @JsonProperty("pCard")
    private String pCard;
    @JsonProperty("pEmail")
    private String pEmail;
    @JsonProperty("pPhone")
    private String pPhone;
    @JsonProperty("pState")
    private Integer pState;
    @JsonProperty("pBirthday")
    private String pBirthday;
    @JsonProperty("pAge")
    private Integer pAge;

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
