package com.example.springboot2.facade;

public class Main {
    public static void main(String[] args) {

        HallLight hallLight = new HallLight();
        hallLight.on();
        LivingLight livingLight = new LivingLight();
        livingLight.on();
        DiningLight diningLight = new DiningLight();
        diningLight.on();

        FacadePattern facadePattern = new FacadePattern();
        facadePattern.lightOn();
    }
}
