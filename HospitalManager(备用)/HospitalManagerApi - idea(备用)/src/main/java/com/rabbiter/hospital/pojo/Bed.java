//这是一个名为Bed的POJO类，用于表示医院的床位信息。
//
//        以下是该类的详细解释：
//
//@TableName(“bed”) - 该注解表示该类对应数据库中的"bed"表。
//
//@TableId(value = “b_id”) - 该注解表示bId字段是表中的主键。
//
//@JsonProperty(“bId”) - 该注解表示bId字段在JSON序列化和反序列化时使用"bId"作为属性名。
//
//private int bId - 表示床位的ID。
//
//private int pId - 表示该床位对应的患者ID。
//
//private int dId - 表示该床位对应的医生ID。
//
//private Integer bState - 表示床位的状态。
//
//private String bStart - 表示床位的启用时间。
//
//private String bReason - 表示床位的原因（例如，患者入院原因）。
//
//@Version - 该注解表示version字段可以用作乐观锁版本控制。
//
//private Integer version - 表示版本号。
//
//        构造函数和getter/setter方法 - 提供了对这些私有字段的访问和修改。
//
//        toString() 方法 - 用于将对象转换为字符串表示，方便输出对象的详细信息。

package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("bed")
public class Bed {
    @TableId(value = "b_id")
    @JsonProperty("bId")
    private int bId;
    @JsonProperty("pId")
    private int pId;
    @JsonProperty("dId")
    private int dId;
    @JsonProperty("bState")
    private Integer bState;
    @JsonProperty("bStart")
    private String bStart;
    @JsonProperty("bReason")
    private String bReason;
    @Version
    private Integer version;

    public Bed() {
    }

    public Bed(int bId, int pId, int dId, Integer bState, String bStart, String bReason, Integer version) {
        this.bId = bId;
        this.pId = pId;
        this.dId = dId;
        this.bState = bState;
        this.bStart = bStart;
        this.bReason = bReason;
        this.version = version;
    }

    public int getBId() {
        return bId;
    }

    public void setBId(int bId) {
        this.bId = bId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public Integer getBState() {
        return bState;
    }

    public void setBState(Integer bState) {
        this.bState = bState;
    }

    public String getBStart() {
        return bStart;
    }

    public void setBStart(String bStart) {
        this.bStart = bStart;
    }

    public String getBReason() {
        return bReason;
    }

    public void setBReason(String bReason) {
        this.bReason = bReason;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Bed{" +
                "bId=" + bId +
                ", pId=" + pId +
                ", dId=" + dId +
                ", bState=" + bState +
                ", bStart='" + bStart + '\'' +
                ", bReason='" + bReason + '\'' +
                ", version=" + version +
                '}';
    }
}
