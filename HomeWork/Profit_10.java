package Traning06;

import java.util.Scanner;

public class Profit_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double oneleva = Double.parseDouble(scanner.nextLine());
        double twoleva = Double.parseDouble(scanner.nextLine());
        double fiveleva = Double.parseDouble(scanner.nextLine());
        double sum = Integer.parseInt(scanner.nextLine());
        double countOneLeva = oneleva * 1;
        double countTwoLeva = twoleva * 2;
        double countFiveLeva = fiveleva * 5;

        for (int i = 0; i <= countOneLeva; i++) {
            for (int j = 0; j <= countTwoLeva; j += 2) {
                for (int k = 0; k <= countFiveLeva; k += 5) {
                    if (i + j + k == sum) {
                        System.out.printf("%d * 1 lv. + %d * 2 lv. + %d * 5 lv. = %.0f lv.\n", i, (j / 2), (k / 5), sum);
                    }
                }
            }
        }
    }
}
