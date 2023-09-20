package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonProperty;

// 表示住院的 床位信息表
@TableName("bed")      // 表示该类对应数据库中的"bed"表
public class Bed {
    @TableId(value = "b_id")
    @JsonProperty("bId")
    private int bId;     // 表示床位的ID
    @JsonProperty("pId")
    private int pId;     // 表示该床位对应的患者ID
    @JsonProperty("dId")
    private int dId;     // 表示床位对应的医生ID
    @JsonProperty("bState")
    private Integer bState;    // 表示床位的状态
    @JsonProperty("bStart")
    private String bStart;     // 表示床位的启用时间
    @JsonProperty("bReason")
    private String bReason;    // 表示床位的原因（例如：患者入院原因）
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
