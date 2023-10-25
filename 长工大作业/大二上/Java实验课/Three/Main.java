import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    protected int employeeId;
    protected String name;
    protected double salary;

    public Employee(int employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    public void show() {
        System.out.print("员工编号: " + employeeId);
        System.out.print(" 姓名: " + name);
        System.out.println(" 工资: " + salary);
    }
}

class Manager extends Employee {
    private String department;

    public Manager(int employeeId, String name, double salary, String department) {
        super(employeeId, name, salary);
        this.department = department;
    }

    @Override
    public void show() {
        System.out.print("员工编号: " + employeeId);
        System.out.print(" 姓名: " + name);
        System.out.print(" 工资: " + salary);
        System.out.println(" 管理部门: " + department);
    }
}

class Director extends Employee {
    private double allowance;

    public Director(int employeeId, String name, double salary, double allowance) {
        super(employeeId, name, salary);
        this.allowance = allowance;
    }

    @Override
    public void show() {
        System.out.print("员工编号: " + employeeId);
        System.out.print(" 姓名: " + name);
        System.out.print(" 工资: " + salary);
        System.out.println(" 津贴: " + allowance);
    }
}

public class Main{
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("员工管理系统菜单：");
            System.out.println("1. 添加员工");
            System.out.println("2. 删除员工");
            System.out.println("3. 更新员工信息");
            System.out.println("4. 显示所有员工");
            System.out.println("5. 退出");
            System.out.print("请输入您的选择: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    System.out.print("请输入员工类型 (雇员/经理/总监): ");
                    String type = scanner.nextLine();

                    System.out.print("请输入员工编号: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // 消耗换行符

                    System.out.print("请输入员工姓名: ");
                    String name = scanner.nextLine();

                    System.out.print("请输入员工工资: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine(); // 消耗换行符

                    if (type.equalsIgnoreCase("雇员")) {
                        employees.add(new Employee(id, name, salary));
                    } else if (type.equalsIgnoreCase("经理")) {
                        System.out.print("请输入管理部门: ");
                        String department = scanner.nextLine();
                        employees.add(new Manager(id, name, salary, department));
                    } else if (type.equalsIgnoreCase("总监")) {
                        System.out.print("请输入津贴: ");
                        double allowance = scanner.nextDouble();
                        scanner.nextLine(); // 消耗换行符
                        employees.add(new Director(id, name, salary, allowance));
                    } else {
                        System.out.println("无效的员工类型。");
                    }
                    break;

                case 2:
                    System.out.print("请输入要删除的员工编号: ");
                    int idToDelete = scanner.nextInt();
                    scanner.nextLine(); // 消耗换行符

                    employees.removeIf(e -> e.employeeId == idToDelete);
                    break;

                case 3:
                    System.out.print("请输入要更新的员工编号: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine(); // 消耗换行符

                    for (Employee emp : employees) {
                        if (emp.employeeId == idToUpdate) {
                            System.out.print("请输入新的工资: ");
                            double newSalary = scanner.nextDouble();
                            scanner.nextLine(); // 消耗换行符
                            emp.salary = newSalary;
                            System.out.println("员工信息已更新。");
                            break;
                        }
                    }
                    break;

                case 4:
                    System.out.println("所有员工：");
                    for (Employee emp : employees) {
                        emp.show();
                    }
                    System.out.println("\n------------------------------");
                    break;

                case 5:
                    System.out.println("退出员工管理系统。");
                    System.exit(0);
                    break;

                default:
                    System.out.println("无效的选择。请提供有效的选项。");
            }
        }
    }
}
