public class Main {
    public static void main(String[] args) {
//        SeqList seqList = new SeqList();
//        for(int i = 0; i < 10; i++){
//            seqList.add(i,i*10);
//        }
//        seqList.display();

        SeqList list = new SeqList();

        // 添加元素并打印列表
        list.add(1);
        list.add(2);
        list.add(3);
        list.display(); // 应该打印 "1 2 3 "

        // 判断是否包含某个元素
        System.out.println(list.contains(2)); // 应该打印 true
        System.out.println(list.contains(4)); // 应该打印 false

        // 查找元素的位置下标
        System.out.println(list.indexOf(3)); // 应该打印 2
        System.out.println(list.indexOf(4)); // 应该打印 -1

        // 获取元素
        System.out.println(list.get(0)); // 应该打印 1
        System.out.println(list.get(2)); // 应该打印 3

        // 获取列表长度
        System.out.println(list.size()); // 应该打印 3

        // 设置元素
        list.set(1, 5);
        list.display(); // 应该打印 "1 5 3 "

        // 在指定位置插入元素
        list.add(1, 4);
        list.display(); // 应该打印 "1 4 5 3 "

        // 删除第一次出现的元素
        list.remove(4);
        list.display(); // 应该打印 "1 5 3 "

        // 清空列表
        list.clear();
        list.display(); // 应该打印空行
        System.out.println(list.size()); // 应该打印 0
    }
}
