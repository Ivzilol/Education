
package Exercises_04;

import java.util.Scanner;

public class CleverLily_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int age = Integer.parseInt(scanner.nextLine());
        double priceWashingMachine = Double.parseDouble(scanner.nextLine());
        int toyPrice = Integer.parseInt(scanner.nextLine());
        double moneyForBD = 0;
        int brother = 0;
        int toys = 0;
        for (int i = 1; i <= age; i++) {
            if (i % 2 == 0){
                if (i == 2){
                    moneyForBD = 10;
                }else {
                    moneyForBD += 10.00 * i / 2;
                }
                brother++;
            }else {
                toys++;

            }

        }
        double toysMoney = toys * toyPrice;
        double totalMoney = moneyForBD + toysMoney - brother;

        if (priceWashingMachine <= totalMoney){
            System.out.printf("Yes! %.2f", totalMoney - priceWashingMachine);
        }else {
            System.out.printf("No! %.2f", priceWashingMachine - totalMoney);
        }

    }
}
