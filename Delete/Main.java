import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vehicle {
    private String id;
    private String make;
    private String model;
    private int year;

    public Vehicle(String id, String make, String model, int year) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Make: " + make + ", Model: " + model + ", Year: " + year;
    }
}

class VehicleManagementSystem {
    private List<Vehicle> vehicles;

    public VehicleManagementSystem() {
        vehicles = new ArrayList<>();
    }

    public void addVehicle(String id, String make, String model, int year) {
        Vehicle vehicle = new Vehicle(id, make, model, year);
        vehicles.add(vehicle);
        System.out.println("车辆添加成功！");
    }

    public void searchVehicle(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                System.out.println("查找结果：\n" + vehicle.toString());
                return;
            }
        }
        System.out.println("未找到该车辆。");
    }

    public void deleteVehicle(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                vehicles.remove(vehicle);
                System.out.println("车辆删除成功！");
                return;
            }
        }
        System.out.println("未找到要删除的车辆。");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleManagementSystem system = new VehicleManagementSystem();

        while (true) {
            System.out.println("\n车辆管理系统");
            System.out.println("1. 添加车辆");
            System.out.println("2. 查询车辆");
            System.out.println("3. 删除车辆");
            System.out.println("4. 退出");
            System.out.print("请选择操作：");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    System.out.print("请输入车辆ID: ");
                    String id = scanner.nextLine();
                    System.out.print("请输入车辆制造商: ");
                    String make = scanner.nextLine();
                    System.out.print("请输入车辆型号: ");
                    String model = scanner.nextLine();
                    System.out.print("请输入车辆年份: ");
                    int year = scanner.nextInt();
                    system.addVehicle(id, make, model, year);
                    break;
                case 2:
                    System.out.print("请输入要查询的车辆ID: ");
                    id = scanner.nextLine();
                    system.searchVehicle(id);
                    break;
                case 3:
                    System.out.print("请输入要删除的车辆ID: ");
                    id = scanner.nextLine();
                    system.deleteVehicle(id);
                    break;
                case 4:
                    System.out.println("谢谢使用，再见！");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("无效的选择，请重新输入。");
                    break;
            }
        }
    }
}
