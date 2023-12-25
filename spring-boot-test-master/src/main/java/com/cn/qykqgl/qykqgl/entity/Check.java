package com.cn.qykqgl.qykqgl.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 考勤表
 */
public class Check implements Serializable {
    private String id;
    private String ryzj;
    private Timestamp scdk;
    private Timestamp zhdk;
    private String lb;
    private String zt;
    private String bz;
    private int ljcqts;
    //在岗时长
    private String zgsc;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date kqrq;

    public Check() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRyzj() {
        return ryzj;
    }

    public void setRyzj(String ryzj) {
        this.ryzj = ryzj;
    }

    public Timestamp getScdk() {
        return scdk;
    }

    public void setScdk(Timestamp scdk) {
        this.scdk = scdk;
    }

    public Timestamp getZhdk() {
        return zhdk;
    }

    public void setZhdk(Timestamp zhdk) {
        this.zhdk = zhdk;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public int getLjcqts() {
        return ljcqts;
    }

    public void setLjcqts(int ljcqts) {
        this.ljcqts = ljcqts;
    }

    public String getZgsc() {
        return zgsc;
    }

    public void setZgsc(String zgsc) {
        this.zgsc = zgsc;
    }

    public Date getKqrq() {
        return kqrq;
    }

    public void setKqrq(Date kqrq) {
        this.kqrq = kqrq;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id='" + id + '\'' +
                ", ryzj='" + ryzj + '\'' +
                ", scdk=" + scdk +
                ", zhdk=" + zhdk +
                ", lb='" + lb + '\'' +
                ", zt='" + zt + '\'' +
                ", bz='" + bz + '\'' +
                ", ljcqts=" + ljcqts +
                ", zgsc='" + zgsc + '\'' +
                ", kqrq=" + kqrq +
                '}';
    }

    public Check(String id, String ryzj, Timestamp scdk, Timestamp zhdk, String lb, String zt, String bz, int ljcqts, String zgsc, Date kqrq) {
        this.id = id;
        this.ryzj = ryzj;
        this.scdk = scdk;
        this.zhdk = zhdk;
        this.lb = lb;
        this.zt = zt;
        this.bz = bz;
        this.ljcqts = ljcqts;
        this.zgsc = zgsc;
        this.kqrq = kqrq;
    }
}
