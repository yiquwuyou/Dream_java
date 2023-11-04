public class MyDoubleList {
    static class ListNode {
        public int val;
        public ListNode prev;
        public ListNode next;

        public ListNode(int val){
            this.val = val;
        }
    }

    public ListNode head;
    public ListNode last;

    // 得到链表的长度，和双向没有关系
    public int size(){
        int len = 0;
        ListNode cur = head;
        while(cur != null){
            len++;
            cur = cur.next;
        }
        return len;
    }

    // 打印链表
    public void display(){
        ListNode cur = head;
        while(cur != null){
            System.out.println(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    // 查找是否包含关键字key是否在链表当中
    public boolean contains(int key){
        ListNode cur = head;
        while(cur != null){
            if(cur.val == key){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 头插
    public void addFirst(int data){
        ListNode node = new ListNode(data);
        if(head == null){
            head =node;
            last =node;
            return;
        }
        node.next = head;
        head.prev = node;
        head = node;
    }

    // 尾插
    public void addLast(int data){
        ListNode node = new ListNode(data);
        if(head == null){
            head = node;
            last = node;
        } else{
            last.next = node;
            node.prev = last;
            last = node;
        }
    }

    // 任意位置插入，第一个数据节点为0号下标
    public void addIndex(int index, int data){
        int size = size();
        if(index < 0 || index > size){
            throw new IndexOutOfBounds("双向链表index不合法!");
        }
        if(index == 0){
            addFirst(data);
            return;
        }
        if(index == size){
            addLast(data);
            return;
        }

        ListNode cur = head;
        while(index != 0){
            cur = cur.next;
            index--;
        }

        ListNode node = new ListNode(data);
        node.next = cur;
        node.prev = cur.prev;
        cur.prev.next = node;
        cur.prev = node;
    }

    // 删除第一次出现关键字为key的节点
    public void remove(int key){
        ListNode cur = head;
        while(cur != null){
            if(cur.val == key){
                // 是头节点的情况
                // 删除头节点
                if(cur == head){
                    // 删除头节点
                    head = head.next;
                    // 只有1个节点的情况
                    if(head == null){
                        last = null;
                    }else {
                        head.prev = null;
                    }
                } else {
                    // 不是头节点
                    cur.prev.next = cur.next;  // 是下面提取出来的共同语句
                    // 是尾节点
                    if(cur.next == null){
//                        cur.prev.next = null;
                        last = cur.prev;
                    } else{
                        // 不是尾节点
//                        cur.prev.next = cur.next;
                        cur.next.prev = cur.prev;
                    }
                }
            }
        }
    }

    // 删除所有值为key的节点
    public void removeAllKey(int key){

    }

    // 清空链表
    public void clear(){

    }
}
