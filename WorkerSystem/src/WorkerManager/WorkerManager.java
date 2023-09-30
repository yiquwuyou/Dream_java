package WorkerManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class WorkerManager {
    public WorkerManager()
    {
        //文件为空
        File file = new File(FilePath);
        if(file.exists() && file.length() == 0)
        {
            this.WorkerIsEmploy = true;
            this.Workernum = 0;
            this.workerarray = null;
        }
        //文件不为空
        else
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(FilePath))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    // 根据空格分隔行数据
                    String[] tokens = line.split("\\s+");

                    int id = -1;
                    String name = "";
                    int age = -1;
                    String department = "";

                    // 遍历分隔后的数据，提取信息
                    for (String token : tokens) {
                        if (token.startsWith("编号：")) {
                            id = Integer.parseInt(token.substring(3)); // 去掉前缀"编号："
                        } else if (token.startsWith("姓名：")) {
                            name = token.substring(3); // 去掉前缀"姓名："
                        } else if (token.startsWith("年龄：")) {
                            age = Integer.parseInt(token.substring(3)); // 去掉前缀"年龄："
                        } else if (token.startsWith("部门：")) {
                            department = token.substring(3); // 去掉前缀"部门："
                        }
                    }

                    // 创建职员对象并添加到数组
                    Worker worker;
                    if (id != -1 && age != -1) {
                        if ("员工".equals(department)) {
                            worker = new Employee(name, department, age, id);
                        } else if ("经理".equals(department)) {
                            worker = new Manager(name, department, age, id);
                        } else if ("老板".equals(department)) {
                            worker = new Boss(name, department, age, id);
                        } else {
                            // 处理无效的部门信息
                            worker = new Employee(name, "员工", age, id);
                        }

                        // 将职员对象添加到数组
                        if (Workernum < MAX_NUM) {
                            workerarray[Workernum++] = worker;
                        } else {
                            System.out.println("数组已满，无法添加更多职员。");
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("读取文件失败：" + e.getMessage());
            }
        }
    }
    final int MAX_NUM = 1000;
    //用数组存放父类引用
    public Worker[] workerarray = new Worker[MAX_NUM];
    public int Workernum;

    public boolean WorkerIsEmploy;

    public String FilePath = "D:\\JavaIDEA\\Java-projects\\WorkerSystem\\src\\WorkerSystem\\Worker.txt";

    public int Find(String name)
    {
        int index = -1;
        for(int i = 0;i<this.Workernum;i++)
        {
            if(this.workerarray[i].Name == name)
            {
                index = i;
                break;
            }
        }
        return index;
    }
}
