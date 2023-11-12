package com.example.springboot2.facade;

import java.util.List;

public class LivingLight implements Light {
    @Override
    public void on() {
        System.out.println("打开客厅灯");
    }

    @Override
    public void off() {
        System.out.println("关闭客厅灯");
    }
}
