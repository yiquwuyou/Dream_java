package src.java;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NewsManagementSystem extends AbstractNewsManagementSystem implements RandomNewsDisplay {
    private Scanner scanner;
    private Random random;

    public static String staticAttribute = "这是一个静态属性";

    // Add a static method
    public static void staticMethod() {
        System.out.println("这是一个静态方法，并且在该方法中打印了静态属性值");
        System.out.println(staticAttribute);
        System.out.println();
    }

    public NewsManagementSystem() {
        newsList = new ArrayList<>();
        scanner = new Scanner(System.in);
        random = new Random();
    }

    // 随机显示一条新闻
    @Override
    public void displayRandomNews() {
        if (newsList.isEmpty()) {
            System.out.println("没有新闻可显示！");
            return;
        }
        int index = random.nextInt(newsList.size());
        News news = newsList.get(index);
        System.out.println("随机新闻：");
        System.out.println("标题：" + news.getTitle());
        System.out.println("内容：" + news.getContent());
        System.out.println("-----------------------");
    }

    public void addNews() {
        System.out.println("请输入新闻标题：");
        String title = scanner.nextLine();
        System.out.println("请输入新闻内容（输入END结束）：");
        StringBuilder content = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("END")) {
            content.append(line);
            content.append("\n");
        }
        News news = new News(title, content.toString());
        newsList.add(news);
        System.out.println("新闻添加成功！");
    }

    public void editNews() {
        System.out.println("请输入要编辑的新闻标题：");
        String title = scanner.nextLine();
        for (News news : newsList) {
            if (news.getTitle().equals(title)) {
                System.out.println("请输入新的新闻内容（输入END结束）：");
                StringBuilder content = new StringBuilder();
                String line;
                while (!(line = scanner.nextLine()).equals("END")) {
                    content.append(line);
                    content.append("\n");
                }
                news.setContent(content.toString());
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