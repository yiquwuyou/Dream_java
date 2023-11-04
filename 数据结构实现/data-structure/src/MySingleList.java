public class MySingleList {
    static class ListNode{
        public int val;
        public ListNode next;

        public ListNode(int val){
            this.val = val;
        }
    }

    public ListNode head;  // 永远指向我的头节点

    // 构造链表实例
    public void createList(){
        ListNode node1 = new ListNode(12);
        ListNode node2 = new ListNode(23);
        ListNode node3 = new ListNode(34);
        ListNode node4 = new ListNode(45);
        ListNode node5 = new ListNode(56);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        this.head = head;
    }

    // 遍历单列表
    public void show(){
        // 这里不是定义了一个节点，这里只是一个引用
        ListNode cur = head;
        while(cur != null){
            System.out.print(cur.val+" ");
            cur = cur.next;
        }
        System.out.println();
    }

    // 从某个节点开始打印单列表
    public void show(ListNode newHead){
        // 这里不是定义了一个节点，这里只是一个引用
        ListNode cur = newHead;
        while(cur != null){
            System.out.print(cur.val+" ");
            cur = cur.next;
        }
        System.out.println();
    }

    // 得到单链表的长度 -> 链表中节点的个数
    public int size(){
        int count = 0;
        ListNode cur = head;
        while(cur != null){
            count++;
            cur = cur.next;
        }
        return count;
    }

    // 查找单链表中是否包含关键字key
    public boolean contains(int key){
        ListNode cur = head;
        while(cur != null){
            // 如果val值是引用类型，那么这里得用equals来进行比较
            if(key == cur.val){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 头插法
    public void addFirst(int data){
        ListNode node = new ListNode(data);
        node.next = head;
        head = node;
    }

    // 尾插法
    public void addLast(int data){
        if(head == null){
            addFirst(data);
        }
        ListNode node = new ListNode(data);
        ListNode cur = head;
        while(cur.next != null){
            cur = cur.next;
        }
        // cur 指向的节点就是尾巴节点
        cur.next = node;
    }

    // 任意位置插入，第一个数据节点为0号下标
    public void addIndex(int index, int data){
        int len = size();
        // 0、判断index位置的合法性
        if(index < 0 || index > len-1){
            throw new IndexOutOfBounds("任意位置插入数据的时候，index位置不合法：" + index);
        }
        if(index == 0){
            addFirst(data);
            return;
        }
        if(index == len){
            addLast(data);
            return;
        }
        ListNode cur = findIndex(index);
        ListNode node = new ListNode(data);
        node.next = cur.next;
        cur.next = node;

    }

    // 找到 index-1 位置的下标 —— 方便插入、删除等
    private ListNode findIndex(int index){
        ListNode cur = head;
        while(index - 1 != 0){
            cur = cur.next;
            index--;
        }
        return cur;  // index-1位置的节点
    }

    // 删除第一次出现关键字为key的节点
    public void remove(int key){
        if(head == null){
            return;
        }
        if(head.val == key){
            head = head.next;
            return;
        }
        ListNode prev =searchPrev(key);
        if(prev == null){
            System.out.println("没有这个数据！");
            return;
        }
        ListNode del = prev.next;
        prev.next = del.next;
    }

    // 查找包含当前数据节点的前一个节点
    private ListNode searchPrev(int key){
        ListNode prev = head;
        while(prev.next != null){
            if(prev.next.val == key){
                return prev;
            } else {
                prev = prev.next;
            }
        }
        return null;
    }

    // 删除所有值为key的节点
    public void removeAllKey(int key){
        if(head == null){
            return;
        }
        ListNode prev = head;
        ListNode cur = head.next;
        while(cur != null){
            if(cur.val == key){
                prev.next =cur.next;
                cur = cur.next;
            } else{
                prev = prev.next;
                cur = cur.next;
            }
        }
        if(head.val == key){
            head = head.next;
        }
    }

    // 清空链表
    public void clear() {
        while(head != null){
            ListNode headNext = head.next;
            head.next = null;
            head =headNext;
        }
    }
}
