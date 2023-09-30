package WorkerManager;

public class Employee extends Worker {

    public Employee(String name,String did,int age,int id)
    {
        this.Name = name;
        this.DeptID = did;
        this.Age = age;
        this.ID = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Name='" + Name + '\'' +
                ", DeptID='" + DeptID + '\'' +
                ", Age=" + Age +
                ", ID=" + ID +
                '}';
    }

    public void SetEmpolyee(String name,int age,String did,int id)
    {
        this.Name = name;
        this.Age = age;
        this.DeptID = did;
        this.ID = id;
    }

    @Override
    public String Showinfo() {
        return("编号："+this.ID+"\t"+"姓名："+this.Name+"\t"+"年龄："+this.Age+"\t"+"部门："+this.DeptID);
    }
    @Override
    public String GetDeptID() {
        return this.DeptID;
    }
    static int count = 0;
}
