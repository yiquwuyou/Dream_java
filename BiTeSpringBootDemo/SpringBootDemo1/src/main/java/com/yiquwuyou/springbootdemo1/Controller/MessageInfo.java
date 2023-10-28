package com.yiquwuyou.springbootdemo1.Controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data    // 组合注解，集成了@Getter @Setter @ToString 等注解
//@ToString
//@Getter
//@Setter
public class MessageInfo {
    private String from;
    private String to;
    private String message;

}
