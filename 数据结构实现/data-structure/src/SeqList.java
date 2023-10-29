import java.util.Arrays;

// 顺序表
public class SeqList {
    private int[] elem;
    private int count;   // 计算当前存储有效元素个数

    private static final int DEFAULT_CAPACITY = 5;  // 初始化内存空间

    // 构造方法，初始化5个空间
    public SeqList(){
        this.elem = new int[5];
    }

    // 打印顺序表
    public void display(){
        for(int i = 0; i < this.count; i++){
            System.out.print(this.elem[i]+" ");
        }
        System.out.println();
    }

    // 判断是否满了，如果满了，自动扩容
    public void fullResize(){
        if(this.elem.length == count){
            elem = Arrays.copyOf(elem, 2*elem.length);
        }
    }
    // 新增元素，默认在数据最后
    public void add(int data){
        fullResize();
        elem[count] = data;
        count++;
    }


    // 判断是否包含某个元素
    public boolean contains(int toFind){
        for(int i = 0; i < this.count; i++){
            if(this.elem[i] == toFind){
                return true;
            }
        }
        return false;
    }

    // 查找某个元素对应的位置下标
    public int indexOf(int toFind){
        for (int i = 0; i < this.count; i++) {
            if(toFind == this.elem[i]){
                return i;
            }
        }
        return -1;  // 表示没有该元素
    }

    // 判断参数是否合法(按有效元素算)
    public boolean checkPos(int pos){
        if(pos < 0 || pos >= this.count){
            return false;
        }
        return true;
    }

    // 获取pos位置的元素
    public int get(int pos){
        if(!checkPos(pos)){
            throw new PosOutBoundsException("get 数据时，位置不合法!");
        }
        return this.elem[pos];
    }

    // 获取顺序表的长度
    public int size(){
        return this.count;
    }

    // 给pos位置的元素设置为value [修改、更新]
    public void set(int pos, int value){
        if(!checkPos(pos)){
            throw new PosOutBoundsException("set 数据时，位置不合法! ");
        }
        this.elem[pos] = value;
    }

    // 在 pos 位置新增元素
    public void add(int pos, int data){
        fullResize();
        if(pos < 0 || pos > this.count){
            throw new PosOutBoundsException("add 数据时，位置不合法！");
        }
        // 挪动数据
        for(int i = this.count; i > pos; i--){
            this.elem[i] = this.elem[i-1];
        }
        // 存数据
        this.elem[pos] = data;
        this.count++;
    }

    // 删除第一次出现的关键字key
    public void remove(int toRemove){
        // 如果列表为空，直接返回
        if(this.count == 0){
            return;
        }
        int index = indexOf(toRemove);
        if(index == -1){
            return;  //没有要删除的关键字
        }
        for(int i = index; i < this.count-1; i++){
            this.elem[i] = this.elem[i+1];
        }
        this.count--;
    }

//    // 删除指定下标的元素
//    public void removeIndex(int pos){
//        // 如果列表为空，直接返回
//        if(this.count == 0){
//            return;
//        }
//        if(!checkPos(pos)){
//            throw new PosOutBoundsException("removeIndex下标不合法");
//        }
//        // 挪动数据
//        for(int i = pos; i < this.count-1;i++){
//            this.elem[i] = this.elem[i+1];
//        }
//        this.count--;
//    }

    // 清空顺序表
    public void clear(){
        this.count = 0;
    }
}
