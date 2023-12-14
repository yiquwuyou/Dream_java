package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

public class UserJwt {
    public static int userId;
    public static String username;
    public static String nickname;
    public static String avatarPath;
    public static String signature;
    public static String sex;
    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        UserJwt.userId = userId;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserJwt.username = username;
    }

    public static String getNickname() {
        return nickname;
    }

    public static void setNickname(String nickname) {
        UserJwt.nickname = nickname;
    }

    public static String getAvatarPath() {
        return avatarPath;
    }

    public static void setAvatarPath(String avatarPath) {
        UserJwt.avatarPath = avatarPath;
    }

    public static String getSignature() {
        return signature;
    }

    public static void setSignature(String signature) {
        UserJwt.signature = signature;
    }

    public static String getSex() {
        return sex;
    }

    public static void setSex(String sex) {
        UserJwt.sex = sex;
    }

    public static String printf() {
        return "UserJwt{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
