package Traning05;

import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class ReportSystem_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int expectedSum = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();


        int nextPay = 0;
        int payCash = 0;
        int payCard = 0;
        int numberPayCash = 0;
        int numberPayCard = 0;
        while (!input.equals("End")) {
            int sum1 = Integer.parseInt(input);
            nextPay++;
            if (nextPay % 2 != 0 && sum1 > 100) {
                System.out.println("Error in transaction!");

            } else if (nextPay % 2 == 0 && sum1 < 10) {
                System.out.println("Error in transaction!");

            } else if (nextPay % 2 != 0) {
                payCash += sum1;
                numberPayCash++;
                expectedSum -= sum1;
                System.out.println("Product sold!");
            } else {
                payCard += sum1;
                numberPayCard++;
                expectedSum -= sum1;
                System.out.println("Product sold!");
            }
            if (expectedSum <= 0){
                break;
            }
            input = scanner.nextLine();
        }
        if (expectedSum <= 0){
            System.out.printf("Average CS: %.2f%n",1.0 *  payCash / numberPayCash);
            System.out.printf("Average CC: %.2f",1.0 *  payCard / numberPayCard);
        }else {
            System.out.println("Failed to collect required money for charity.");
        }
    }
}
