package com.cn.qykqgl.qykqgl.entity;

import java.io.Serializable;

/**
 * 部门表
 */
public class Dep implements Serializable {
    private String id;
    private String bmbh;
    private String bmmc;

    public Dep(String id, String bmbh, String bmmc) {
        this.id = id;
        this.bmbh = bmbh;
        this.bmmc = bmmc;
    }

    public Dep() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    @Override
    public String toString() {
        return "Dep{" +
                "id='" + id + '\'' +
                ", bmbh='" + bmbh + '\'' +
                ", bmmc='" + bmmc + '\'' +
                '}';
    }
}
