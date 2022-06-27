package Traning04;

import com.sun.source.tree.IfTree;

import java.util.Scanner;

public class GameOfIntervals_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfNumber = Integer.parseInt(scanner.nextLine());
        double count = 0;
        double number0To9 = 0;
        double number10To19 = 0;
        double number20To29 = 0;
        double number30To39 = 0;
        double number40To49 = 0;
        double invalidNumber = 0;


        for (int i = 1; i <= numberOfNumber; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            if (number >= 0 && number <= 9) {
                count += number * 0.20;
                number0To9 += 1;
            } else if (number >= 10 && number <= 19) {
                count += number * 0.30;
                number10To19 += 1;
            } else if (number >= 20 && number <= 29) {
                count += number * 0.40;
                number20To29 += 1;
            } else if (number >= 30 && number <= 39) {
                count += 50;
                number30To39 += 1;
            } else if (number >= 40 && number <= 50) {
                count += 100;
                number40To49 += 1;
            } else {
                count = count / 2;
                invalidNumber += 1;
            }
        }
        System.out.printf("%.2f%n", count);
        System.out.printf("From 0 to 9: %.2f%%%n", number0To9 / numberOfNumber * 100);
        System.out.printf("From 10 to 19: %.2f%%%n", number10To19 / numberOfNumber * 100);
        System.out.printf("From 20 to 29: %.2f%%%n", number20To29 / numberOfNumber * 100);
        System.out.printf("From 30 to 39: %.2f%%%n", number30To39 / numberOfNumber * 100);
        System.out.printf("From 40 to 50: %.2f%%%n", number40To49 / numberOfNumber * 100);
        System.out.printf("Invalid numbers: %.2f%%", invalidNumber / numberOfNumber * 100);
    }
}
