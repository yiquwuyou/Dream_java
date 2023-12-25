package com.cn.qykqgl.qykqgl.entity;


import java.io.Serializable;
/**用户表*/
public class User implements Serializable {
    private String id;
    private String name;
    private String password;
    private String ssbm;
    private String zjhm;
    private String username;
    private String lxdh;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", ssbm='" + ssbm + '\'' +
                ", zjhm='" + zjhm + '\'' +
                ", username='" + username + '\'' +
                ", lxdh='" + lxdh + '\'' +
                '}';
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsbm() {
        return ssbm;
    }

    public void setSsbm(String ssbm) {
        this.ssbm = ssbm;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
