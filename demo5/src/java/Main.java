package src.java;

import src.java.NewsManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NewsManagementSystem newsSystem = new NewsManagementSystem();
        boolean running = true;

        while (running) {
            System.out.println("新闻管理系统菜单：");
            System.out.println("1. 添加新闻");
            System.out.println("2. 编辑新闻");
            System.out.println("3. 删除新闻");
            System.out.println("4. 显示新闻");
            System.out.println("5. 退出");
            System.out.println("请选择操作：");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    newsSystem.addNews();
                    break;
                case 2:
                    newsSystem.editNews();
                    break;
                case 3:
                    newsSystem.deleteNews();
                    break;
                case 4:
                    newsSystem.displayNews();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("无效的选项，请重新选择！");
            }
        }

        newsSystem.close();
    }
}

