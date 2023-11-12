package src.java;

import java.util.List;

public abstract class AbstractNewsManagementSystem {
    protected List<News> newsList;

    public static String staticAttribute;
    public static void staticMethod() {}

    public abstract void addNews();
    public abstract void editNews();
    public abstract void deleteNews();
    public abstract void displayNews();
    public abstract void close();
}