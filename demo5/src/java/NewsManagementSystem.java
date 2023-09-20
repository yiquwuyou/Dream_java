package src.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewsManagementSystem {
    private List<News> newsList;
    private Scanner scanner;

    public NewsManagementSystem() {
        newsList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addNews() {
        System.out.println("请输入新闻标题：");
        String title = scanner.nextLine();
        System.out.println("请输入新闻内容：");
        String content = scanner.nextLine();
        News news = new News(title, content);
        newsList.add(news);
        System.out.println("新闻添加成功！");
    }

    public void editNews() {
        System.out.println("请输入要编辑的新闻标题：");
        String title = scanner.nextLine();
        for (News news : newsList) {
            if (news.getTitle().equals(title)) {
                System.out.println("请输入新的新闻内容：");
                String content = scanner.nextLine();
                news.setContent(content);
                System.out.println("新闻编辑成功！");
                return;
            }
        }
        System.out.println("未找到该新闻标题！");
    }

    public void deleteNews() {
        System.out.println("请输入要删除的新闻标题：");
        String title = scanner.nextLine();
        for (News news : newsList) {
            if (news.getTitle().equals(title)) {
                newsList.remove(news);
                System.out.println("新闻删除成功！");
                return;
            }
        }
        System.out.println("未找到该新闻标题！");
    }

    public void displayNews() {
        if (newsList.isEmpty()) {
            System.out.println("没有新闻可显示！");
            return;
        }
        System.out.println("新闻列表：");
        for (News news : newsList) {
            System.out.println("标题：" + news.getTitle());
            System.out.println("内容：" + news.getContent());
            System.out.println("-----------------------");
        }
    }

    public void close() {
        scanner.close();
    }
}

