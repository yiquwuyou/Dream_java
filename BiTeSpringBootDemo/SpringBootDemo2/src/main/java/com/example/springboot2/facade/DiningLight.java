package com.example.springboot2.facade;

public class DiningLight implements Light{
    @Override
    public void on() {
//        System.out.println("升级......");
        System.out.println("打开餐厅灯");
    }

    @Override
    public void off() {
        System.out.println("关闭餐厅灯");
    }
}
