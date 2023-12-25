package com.cn.qykqgl.qykqgl.entity;

import java.io.Serializable;

/**
 * 考勤专员表
 */
public class Com implements Serializable {
    private String id;
    private String gh;
    private String xm;
    private String xb;
    private String dy;
    private String kqhdrx;

    public Com() {
    }

    public Com(String id, String gh, String xm, String xb, String dy, String kqhdrx) {
        this.id = id;
        this.gh = gh;
        this.xm = xm;
        this.xb = xb;
        this.dy = dy;
        this.kqhdrx = kqhdrx;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
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

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public String getKqhdrx() {
        return kqhdrx;
    }

    public void setKqhdrx(String kqhdrx) {
        this.kqhdrx = kqhdrx;
    }

    @Override
    public String toString() {
        return "Com{" +
                "id='" + id + '\'' +
                ", gh='" + gh + '\'' +
                ", xm='" + xm + '\'' +
                ", xb='" + xb + '\'' +
                ", dy='" + dy + '\'' +
                ", kqhdrx='" + kqhdrx + '\'' +
                '}';
    }
}
