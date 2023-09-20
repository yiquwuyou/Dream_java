package com.example.demo.delete;

public class Student {
    private int id;
    private String name;
    public static int n_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Student() {
        System.out.println("啥也没传！");
        n_id++;
    }
    public Student(int id,String name) {
        this.id = id;
        this.name = name;
        n_id++;
    }
    public void upDate(int id, String name){
        this.id = id;
        this.name = name;
    }


}

class Print_1{
    public void print_2(){
        System.out.println("这是类中定义了一个类");
    }
}
