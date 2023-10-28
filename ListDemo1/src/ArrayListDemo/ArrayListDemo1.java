package src.ArrayListDemo;

import java.util.ArrayList;
import java.util.Iterator;

class ArrayListDemo1 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList =  new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        Iterator<Integer> it = arrayList.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next() + " ");
        }
        System.out.println();
//        Iterator<Integer> iterator = arrayList.iterator();
//
//        // 使用迭代器遍历并打印元素
//        while (iterator.hasNext()) {
//            Integer number = iterator.next();
//            System.out.print(number + " ");
//        }
    }
}
