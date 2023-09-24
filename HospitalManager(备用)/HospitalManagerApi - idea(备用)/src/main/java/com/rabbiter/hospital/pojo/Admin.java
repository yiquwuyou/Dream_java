//这是一个名为Admin的POJO类，用于表示医院的管理员。
//
//        以下是该类的详细解释：
//
//@TableName(“admini”) - 该注解表示该类对应数据库中的"admini"表。
//
//@TableId(value = “a_id”) - 该注解表示aId字段是表中的主键。
//
//@JsonProperty(“aId”) - 该注解表示aId字段在JSON序列化和反序列化时使用"aId"作为属性名。
//
//private int aId - 表示管理员的ID。
//
//private String aPassword - 表示管理员的密码。
//
//private String aName - 表示管理员的姓名。
//
//private String aGender - 表示管理员的性别。
//
//private String aCard - 表示管理员的身份证号。
//
//private String aEmail - 表示管理员的电子邮箱。
//
//private String aPhone - 表示管理员的电话号码。
//
//        构造函数和getter/setter方法 - 提供了对这些私有字段的访问和修改。
//
//        toString() 方法 - 用于将对象转换为字符串表示，方便输出对象的详细信息。

package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("admini")
public class Admin {
    @TableId(value = "a_id")
    @JsonProperty("aId")
    private int aId;
    @JsonProperty("aPassword")
    private String aPassword;
    @JsonProperty("aName")
    private String aName;
    @JsonProperty("aGender")
    private String aGender;
    @JsonProperty("aCard")
    private String aCard;
    @JsonProperty("aEmail")
    private String aEmail;
    @JsonProperty("aPhone")
    private String aPhone;


    public Admin() {
    }


    public Admin(int aId, String aPassword, String aName, String aGender, String aCard, String aEmail, String aPhone) {
        this.aId = aId;
        this.aPassword = aPassword;
        this.aName = aName;
        this.aGender = aGender;
        this.aCard = aCard;
        this.aEmail = aEmail;
        this.aPhone = aPhone;
    }

    public int getAId() {
        return aId;
    }

    public void setAId(int aId) {
        this.aId = aId;
    }

    public String getAPassword() {
        return aPassword;
    }

    public void setAPassword(String aPassword) {
        this.aPassword = aPassword;
    }

    public String getAName() {
        return aName;
    }

    public void setAName(String aName) {
        this.aName = aName;
    }

    public String getAGender() {
        return aGender;
    }

    public void setAGender(String aGender) {
        this.aGender = aGender;
    }

    public String getACard() {
        return aCard;
    }

    public void setACard(String aCard) {
        this.aCard = aCard;
    }

    public String getAEmail() {
        return aEmail;
    }

    public void setAEmail(String aEmail) {
        this.aEmail = aEmail;
    }

    public String getAPhone() {
        return aPhone;
    }

    public void setAPhone(String aPhone) {
        this.aPhone = aPhone;
    }


    @Override
    public String toString() {
        return "Admin{" +
                "aId=" + aId +
                ", aPassword='" + aPassword + '\'' +
                ", aName='" + aName + '\'' +
                ", aGender='" + aGender + '\'' +
                ", aCard='" + aCard + '\'' +
                ", aEmail='" + aEmail + '\'' +
                ", aPhone='" + aPhone + '\'' +
                '}';
    }
}
