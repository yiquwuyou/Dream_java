import java.util.Scanner;

class ATM {
    private String account;   // 账号
    private String cardholder;   // 持卡人
    private double balance;     // 余额

    public ATM(String account, String cardholder, double initialBalance) {
        this.account = account;
        this.cardholder = cardholder;
        this.balance = initialBalance;
    }

    // 查询余额
    public void displayBalance() {
        System.out.println("账号: " + account);
        System.out.println("持卡人: " + cardholder);
        System.out.println("余额: ￥" + balance);
    }

    // 存款
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存款成功，存入金额：￥" + amount);
        } else {
            System.out.println("无效的存款金额。");
        }
    }

    // 取款
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("取款成功，取出金额：￥" + amount);
        } else {
            System.out.println("无效的取款金额或余额不足。");
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM("123456789", "李四", 500.0);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM菜单:");
            System.out.println("1. 显示余额");
            System.out.println("2. 存款");
            System.out.println("3. 取款");
            System.out.println("4. 退出");
            System.out.print("请输入您的选择: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.displayBalance();
                    break;
                case 2:
                    System.out.print("请输入存款金额：￥");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("请输入取款金额：￥");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("感谢您使用ATM。再见！");
                    break;
                default:
                    System.out.println("无效的选择，请重试。");
            }
        } while (choice != 4);

        scanner.close();
    }
}
