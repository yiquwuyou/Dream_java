package com.example.questionnaire.model;


public class UserJwt {
    public static int userId;
    public static String username;
    public static String phone;
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

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        UserJwt.phone = phone;
    }

    public static String printf() {
        return "UserJwt{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
