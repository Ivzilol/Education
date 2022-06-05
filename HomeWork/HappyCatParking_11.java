package Traning06;

import java.util.Scanner;

public class HappyCatParking_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = Integer.parseInt(scanner.nextLine());
        int hours = Integer.parseInt(scanner.nextLine());
        int countDays = 0;
        double tax = 0;
        double taxDays = 0;
        double totalTax = 0;
        for (int i = 1; i <= days; i++) {
            countDays++;
            taxDays = 0;
            for (int j = 1; j <= hours; j++) {
                if (i % 2 == 0 && j % 2 != 0) {
                    tax = 2.50;
                    taxDays += tax;
                    totalTax += tax;
                } else if (i % 2 != 0 && j % 2 == 0) {
                    tax = 1.25;
                    totalTax += tax;
                    taxDays += tax;
                } else {
                    tax = 1.00;
                    taxDays += tax;
                    totalTax += tax;
                }
            }
            System.out.printf("Day: %d - %.2f leva\n", countDays, taxDays);
        }
        System.out.printf("Total: %.2f leva", totalTax);
    }
}
