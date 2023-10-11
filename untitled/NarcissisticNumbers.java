//public class NarcissisticNumbers {
//    public static void main(String[] args) {
//        for (int num = 100; num <= 999; num++) {
//            int originalNum = num;
//            int sum = 0;
//
//            int a = num;
//            while (a > 0) {
//                int digit = a % 10;
//                sum += digit * digit * digit;
//                a /= 10;
//            }
//
//
//            if (sum == originalNum) {
//                System.out.println(originalNum + " 是水仙花数");
//            }
//        }
//    }
//}

import java.util.Scanner;

public class NarcissisticNumbers {
    public static void main(String[] args) {
        for (int number = 100; number <= 999; number++) {
            int digit1 = number / 100; // 百位
            int digit2 = (number / 10) % 10; // 十位
            int digit3 = number % 10; // 个位

            // 通过水仙花法则算出来的数
            int sumOfCubes = (int) (Math.pow(digit1, 3) + Math.pow(digit2, 3) + Math.pow(digit3, 3));

            if (sumOfCubes == number) {
                System.out.println(number + " 是水仙花数");
            }
        }
    }
}

