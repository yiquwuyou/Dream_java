package com.bite.book.adapter;

public class Slf4jLog4jAdapter implements Slf4jApi{

    private Log4j log4j;

    public Slf4jLog4jAdapter(Log4j log4j) {
        this.log4j = log4j;
    }

    @Override
    public void log(String message) {
        log4j.log(message);
    }
}
