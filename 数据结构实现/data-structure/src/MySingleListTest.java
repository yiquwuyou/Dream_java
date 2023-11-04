public class MySingleListTest {
    public static void main(String[] args) {
        MySingleList list = new MySingleList();

        // 测试头插法
        list.addFirst(10);
        list.addFirst(20);
        list.show(); // 应输出: 20 10

        // 测试尾插法
        list.addLast(30);
        list.addLast(40);
        list.show(); // 应输出: 20 10 30 40

        // 测试任意位置插入
        list.addIndex(2, 50);
        list.show(); // 应输出: 20 10 50 30 40

        // 测试删除节点
        list.remove(10);
        list.show(); // 应输出: 20 50 30 40

        // 测试删除所有值为key的节点
        list.addLast(30);
        list.addLast(30);
        list.removeAllKey(30);
        list.show(); // 应输出: 20 50 40

        // 测试链表清空
        list.clear();
        list.show(); // 应输出: 空

        // 测试链表包含关键字
        list.addLast(60);
        list.addLast(70);
        list.addLast(80);
        boolean contains60 = list.contains(60);
        boolean contains90 = list.contains(90);
        System.out.println("包含60吗？" + contains60); // 应输出: true
        System.out.println("包含90吗？" + contains90); // 应输出: false

        // 测试链表长度
        int size = list.size();
        System.out.println("链表长度为：" + size); // 应输出: 3
    }
}
