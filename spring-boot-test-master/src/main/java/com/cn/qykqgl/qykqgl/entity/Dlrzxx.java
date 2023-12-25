package com.cn.qykqgl.qykqgl.entity;

import java.io.Serializable;

/**
 * 登录日志信息表
 */
public class Dlrzxx implements Serializable {
    private String id;
    private String dlsj;
    private String dlyh;

    @Override
    public String toString() {
        return "Dlrzxx{" +
                "id='" + id + '\'' +
                ", dlsj='" + dlsj + '\'' +
                ", dlyh='" + dlyh + '\'' +
                '}';
    }

    public Dlrzxx() {
    }

    public Dlrzxx(String id, String dlsj, String dlyh) {
        this.id = id;
        this.dlsj = dlsj;
        this.dlyh = dlyh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDlsj() {
        return dlsj;
    }

    public void setDlsj(String dlsj) {
        this.dlsj = dlsj;
    }

    public String getDlyh() {
        return dlyh;
    }

    public void setDlyh(String dlyh) {
        this.dlyh = dlyh;
    }
}
