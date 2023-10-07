package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 基类 - 用户
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

// 学生类 - 继承自用户类
// 学生类 - 继承自用户类
class Student extends User {
    private String studentId;
    private List<String> assignedRooms;

    public Student(String username, String password, String studentId) {
        super(username, password);
        this.studentId = studentId;
        this.assignedRooms = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void addAssignedRoom(String roomNumber) {
        assignedRooms.add(roomNumber);
    }

    public List<String> getAssignedRooms() {
        return assignedRooms;
    }

    // 添加 getUsername 方法
    public String getUsername() {
        return super.getUsername();
    }
}


// 宿舍管理员类 - 继承自用户类
class DormitoryManager extends User {
    public DormitoryManager(String username, String password) {
        super(username, password);
    }

    public void assignRoom(Student student, String roomNumber) {
        student.addAssignedRoom(roomNumber);
        System.out.println("分配成功！");
    }

    public void viewAssignedRooms(Student student) {
        List<String> assignedRooms = student.getAssignedRooms();
        if (assignedRooms.isEmpty()) {
            System.out.println("该学生没有分配的宿舍房间。");
        } else {
            System.out.println("学生 " + student.getStudentId() + " 的分配的宿舍房间：");
            for (String room : assignedRooms) {
                System.out.println(room);
            }
        }
    }
}

public class DormitoryManagementSystem {
    public static void main(String[] args) {
        DormitoryManager manager = new DormitoryManager("admin", "admin123");
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("宿舍管理系统菜单：");
            System.out.println("1. 添加学生");
            System.out.println("2. 分配宿舍房间");
            System.out.println("3. 查看学生分配的宿舍房间");
            System.out.println("4. 退出");
            System.out.println("请选择操作：");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("请输入学生用户名：");
                    String studentUsername = scanner.next();
                    System.out.println("请输入学生密码：");
                    String studentPassword = scanner.next();
                    System.out.println("请输入学生学号：");
                    String studentId = scanner.next();
                    Student student = new Student(studentUsername, studentPassword, studentId);
                    students.add(student);
                    System.out.println("学生添加成功！");
                    break;
                case 2:
                    System.out.println("请输入学生用户名：");
                    String assignUsername = scanner.next();
                    System.out.println("请输入宿舍房间号：");
                    String roomNumber = scanner.next();
                    Student assignedStudent = findStudentByUsername(students, assignUsername);
                    if (assignedStudent != null) {
                        manager.assignRoom(assignedStudent, roomNumber);
                    } else {
                        System.out.println("未找到该学生！");
                    }
                    break;
                case 3:
                    System.out.println("请输入学生用户名：");
                    String viewUsername = scanner.next();
                    Student viewStudent = findStudentByUsername(students, viewUsername);
                    if (viewStudent != null) {
                        manager.viewAssignedRooms(viewStudent);
                    } else {
                        System.out.println("未找到该学生！");
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("无效的选项，请重新选择！");
            }
        }

        scanner.close();
    }

    private static Student findStudentByUsername(List<Student> students, String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        return null;
    }
}