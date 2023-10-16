import java.util.ArrayList;
import java.util.Scanner;

class Staff {
    private int employeeId;    // 员工ID
    private String name;       // 员工姓名
    private int age;           // 员工年龄
    private double salary;     // 员工薪资

    public Staff(int employeeId, String name, int age, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // 显示员工信息
    public void displayEmployeeInfo() {
        System.out.println("员工编号: " + employeeId);
        System.out.println("员工姓名: " + name);
        System.out.println("员工年龄: " + age);
        System.out.println("员工工资: ￥" + salary);
    }

    // 添加员工信息
    public void setEmployeeInfo(int employeeId, String name, int age, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Staff> employeeList = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n员工信息管理菜单:");
            System.out.println("1. 显示员工信息");
            System.out.println("2. 添加新员工信息");
            System.out.println("3. 退出");
            System.out.print("请输入您的选择: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (employeeList.isEmpty()) {
                        System.out.println("员工列表为空。");
                    } else {
                        for (Staff employee : employeeList) {
                            employee.displayEmployeeInfo();
                            System.out.println("--------------");
                        }
                    }
                    break;
                case 2:
                    System.out.print("请输入员工编号: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine(); // 消费换行符
                    System.out.print("请输入员工姓名: ");
                    String name = scanner.nextLine();
                    System.out.print("请输入员工年龄: ");
                    int age = scanner.nextInt();
                    System.out.print("请输入员工工资: ￥");
                    double salary = scanner.nextDouble();

                    Staff newEmployee = new Staff(employeeId, name, age, salary);
                    employeeList.add(newEmployee);
                    System.out.println("新员工信息已添加成功！");
                    break;
                case 3:
                    System.out.println("感谢您使用员工信息管理系统。再见！");
                    break;
                default:
                    System.out.println("无效的选择，请重试。");
            }
        } while (choice != 3);

        scanner.close();
    }
}
