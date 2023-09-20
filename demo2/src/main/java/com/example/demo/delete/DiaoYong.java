package com.example.demo.delete;

public class DiaoYong {
    public static void main(String[] args) {
        Student student = new Student(5,"love");
        System.out.println("student : " + student);
        Student student1 = new Student();
        System.out.println("student1 : " + student1);
        student.upDate(6,"anxiaoyi");
        System.out.println("student : " + student);

        // 这是在 student 里面又定义了一个类
        // 不符合规范但确实可以
        Print_1 print1 = new Print_1();
        print1.print_2();
        System.out.println(student.getId());
        student.setId(666);
        System.out.println(student.getId());
        System.out.println(student.n_id);

    }

}
