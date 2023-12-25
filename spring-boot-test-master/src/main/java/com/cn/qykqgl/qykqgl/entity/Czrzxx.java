package com.cn.qykqgl.qykqgl.entity;

import java.io.Serializable;

/**
 * 操作日志信息表
 */
public class Czrzxx implements Serializable {
    private String id;
    private String czsj;
    private String bz;
    private String czyh;

    public Czrzxx() {
    }

    public Czrzxx(String id, String czsj, String bz, String czyh) {
        this.id = id;
        this.czsj = czsj;
        this.bz = bz;
        this.czyh = czyh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCzsj() {
        return czsj;
    }

    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCzyh() {
        return czyh;
    }

    public void setCzyh(String czyh) {
        this.czyh = czyh;
    }

    @Override
    public String toString() {
        return "Czrzxx{" +
                "id='" + id + '\'' +
                ", czsj='" + czsj + '\'' +
                ", bz='" + bz + '\'' +
                ", czyh='" + czyh + '\'' +
                '}';
    }
}
