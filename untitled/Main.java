import java.util.Scanner;

// 银行卡的抽象类，定义了共同的属性和抽象方法
abstract class BankCard {
    private String cardNumber;   // 银行卡号
    private String cardHolder;    // 持卡人姓名
    protected double balance;     // 余额

    public BankCard(String cardNumber, String cardHolder, double balance) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.balance = balance;
    }

    public void displayBalance() {
        System.out.println("卡号：" + cardNumber);
        System.out.println("持卡人：" + cardHolder);
        System.out.println("余额：￥" + balance);
    }

    public abstract void withdraw(double amount);
}

// 信用卡类，继承自银行卡类
class CreditCard extends BankCard {
    private double cashLimit;    // 取现额度

    public CreditCard(String cardNumber, String cardHolder, double cashLimit, double balance) {
        super(cardNumber, cardHolder, balance);
        this.cashLimit = cashLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= cashLimit && amount <= balance) {
            balance -= (amount + amount * 0.01); // 手续费1%
            System.out.println("从信用卡取现：￥" + amount);
        } else {
            System.out.println("无效的取款金额或取现额度不足。");
        }
    }
}

// 借记卡
class DebitCard extends BankCard {
    public DebitCard(String cardNumber, String cardHolder, double balance) {
        super(cardNumber, cardHolder, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("从借记卡取现：￥" + amount);
        } else {
            System.out.println("无效的取款金额或余额不足。");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CreditCard creditCard = new CreditCard("1234-5678-9012-3456", "花花同学", 5000.0, 5000.0);
        DebitCard debitCard = new DebitCard("9876-5432-1098-7654", "花花同学", 2000.0);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n菜单:");
            System.out.println("1. 查询信用卡余额");
            System.out.println("2. 从信用卡取款");
            System.out.println("3. 查询借记卡余额");
            System.out.println("4. 从借记卡取款");
            System.out.println("5. 退出");
            System.out.print("请输入您的选择: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    creditCard.displayBalance();
                    break;
                case 2:
                    System.out.print("请输入取款金额：￥");
                    double creditWithdraw = scanner.nextDouble();
                    creditCard.withdraw(creditWithdraw);
                    break;
                case 3:
                    debitCard.displayBalance();
                    break;
                case 4:
                    System.out.print("请输入取款金额：￥");
                    double debitWithdraw = scanner.nextDouble();
                    debitCard.withdraw(debitWithdraw);
                    break;
                case 5:
                    System.out.println("感谢您使用银行卡系统。再见！");
                    break;
                default:
                    System.out.println("无效的选择，请重试。");
            }
        } while (choice != 5);

        scanner.close();
    }
}
