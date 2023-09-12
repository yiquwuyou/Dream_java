package com.example.demo.delete;

public class DiaoYong {
    public static void main(String[] args) {
        Student student = new Student(5,"love");
        System.out.println(student);
        Student student1 = new Student();
        System.out.println(student1);
        student.upDate(6,"anxiaoyi");
        System.out.println(student);
    }

}
