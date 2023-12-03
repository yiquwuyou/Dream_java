package com.bite.book.enums;

public enum ResultCode {
    SUCCESS(0),
    FAIL(-1),
    UNLOGIN(-2);

    //0-成功  -1 失败  -2 未登录
    private int code;

    ResultCode(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
