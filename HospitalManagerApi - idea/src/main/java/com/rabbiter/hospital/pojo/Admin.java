package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

// 在 pojo 中 注释最全
// 在java中，实体类通常定义在 pojo、entity包 等下面（多选一）
// 管理员表实体类
@TableName("admini")    // 表示该类对应数据库中的 “admini” 表
public class Admin {
    @TableId(value = "a_id")    // 该注解表示 aId 字段是表中的主键
    @JsonProperty("aId")        // 该注解表示 aId 字段在 JSON 序列化和反序列化时使用“aId”作为属性名
    private int aId;            // 管理员的 ID（账号）
    @JsonProperty("aPassword")
    private String aPassword;    // 管理员的密码
    @JsonProperty("aName")
    private String aName;       // 管理员的名字
    @JsonProperty("aGender")
    private String aGender;     // 管理员的性别
    @JsonProperty("aCard")
    private String aCard;       // 管理员的 身份证号（位数必须一致）
    @JsonProperty("aEmail")
    private String aEmail;      // 管理员的电子邮箱
    @JsonProperty("aPhone")
    private String aPhone;      // 管理员的电话号码

    // 空参数的构造方法，在没有参数的情况下创建一个初始化的 Admin 对象，该操作方法没有执行任何具体的操作或属性初始化
    // 空参数的构造方法常用于以下情况：
    // 1、需要创建一个默认的对象，该对象的属性将在稍后的时间点进行单独的设置。
    // 2、子类需要调用父类的空参数构造方法。
    // 代码举例： Admin admin = new Admin();
    public Admin() {
    }

    // 构造方法  接收多个参数用于初始化 Admin 对象的属性
    // 调用和对象创建的示例代码：
    // Admin admin = new Admin(1, "password", "John Doe", "Male", "1234567890", "john@example.com", "1234567890");
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

    // toString 本质就是打印对象（输出对象时输出toString内的内容）
    // 将打印出来的内容由对象转变为字符串
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
