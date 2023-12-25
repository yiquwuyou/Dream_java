package com.cn.qykqgl.qykqgl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/***
    学生表
 */
public class Sta implements Serializable {
    private String id;
    private String gh;
    private String xm;
    private String xb;
    private String lxdh;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jrsj;
    private String zjhm;
    private String ssbm;
    private String bmbh;
    private String bz;
    private Timestamp cjsj;
    private String cjyh;
    private Timestamp xgsj;
    private String xgyh;
    private int nl;

    public Sta(String id, String gh, String xm, String xb, String lxdh, Date jrsj, String zjhm, String ssbm, String bmbh, String bz, Timestamp cjsj, String cjyh, Timestamp xgsj, String xgyh, int nl) {
        this.id = id;
        this.gh = gh;
        this.xm = xm;
        this.xb = xb;
        this.lxdh = lxdh;
        this.jrsj = jrsj;
        this.zjhm = zjhm;
        this.ssbm = ssbm;
        this.bmbh = bmbh;
        this.bz = bz;
        this.cjsj = cjsj;
        this.cjyh = cjyh;
        this.xgsj = xgsj;
        this.xgyh = xgyh;
        this.nl = nl;
    }

    public Sta() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public Date getJrsj() {
        return jrsj;
    }

    public void setJrsj(Date jrsj) {
        this.jrsj = jrsj;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getSsbm() {
        return ssbm;
    }

    public void setSsbm(String ssbm) {
        this.ssbm = ssbm;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Timestamp getCjsj() {
        return cjsj;
    }

    public void setCjsj(Timestamp cjsj) {
        this.cjsj = cjsj;
    }

    public String getCjyh() {
        return cjyh;
    }

    public void setCjyh(String cjyh) {
        this.cjyh = cjyh;
    }

    public Timestamp getXgsj() {
        return xgsj;
    }

    public void setXgsj(Timestamp xgsj) {
        this.xgsj = xgsj;
    }

    public String getXgyh() {
        return xgyh;
    }

    public void setXgyh(String xgyh) {
        this.xgyh = xgyh;
    }

    public int getNl() {
        return nl;
    }

    public void setNl(int nl) {
        this.nl = nl;
    }

    @Override
    public String toString() {
        return "Sta{" +
                "id='" + id + '\'' +
                ", gh='" + gh + '\'' +
                ", xm='" + xm + '\'' +
                ", xb='" + xb + '\'' +
                ", lxdh='" + lxdh + '\'' +
                ", jrsj=" + jrsj +
                ", zjhm='" + zjhm + '\'' +
                ", ssbm='" + ssbm + '\'' +
                ", bmbh='" + bmbh + '\'' +
                ", bz='" + bz + '\'' +
                ", cjsj=" + cjsj +
                ", cjyh='" + cjyh + '\'' +
                ", xgsj=" + xgsj +
                ", xgyh='" + xgyh + '\'' +
                ", nl='" + nl + '\'' +
                '}';
    }
}
