package com.bite.book.adapter;

public class Main {
    public static void main(String[] args) {
        Slf4jApi api = new Slf4jLog4jAdapter(new Log4j());
        api.log("我是通过slf4j打印的日志");
    }
}
