import java.util.Scanner;

public class SymmetricalDiamondWithSpacing {

    public static void Print(int n){
        int spaces = n / 2;
        int stars = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < spaces; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j < stars; j++) {
                System.out.print("* ");
            }
            System.out.println();

            if (i <= n / 2) {
                spaces--;
                stars += 2;
            } else {
                spaces++;
                stars -= 2;
            }
        }

        for (int i = n / 2; i >= 1; i--) {
            spaces++;
            stars -= 2;

            for (int j = 0; j < spaces; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j < stars; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一个奇数 n：");
        int n = scanner.nextInt();

        if (n % 2 == 0) {
            System.out.println("请输入一个奇数以生成对称菱形。");
            return;
        }
        SymmetricalDiamondWithSpacing.Print(n);
    }
}
