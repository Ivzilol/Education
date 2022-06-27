package Traning05;

import java.util.Scanner;

public class Dishwasher_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int bottlesPrep = Integer.parseInt(scanner.nextLine());

        double prepMl = bottlesPrep * 750;
        int plate = 5;
        int pot = 15;
        int numberCharging = 0;
        int numberPlate = 0;
        int numberPot = 0;


        while (prepMl >= 0) {
            String washingDishes = scanner.nextLine();
            if (washingDishes.equals("End")) {
                break;
            }
            int numberWashingDishes = Integer.parseInt(washingDishes);
            numberCharging++;
            if (numberCharging % 3 == 0) {
                prepMl -= pot * numberWashingDishes;
                numberPot += numberWashingDishes;
            } else {
                prepMl -= plate * numberWashingDishes;
                numberPlate += numberWashingDishes;
            }

        }
        if (prepMl >= 0) {
            System.out.println("Detergent was enough!");
            System.out.printf("%d dishes and %d pots were washed.%n", numberPlate, numberPot);
            System.out.printf("Leftover detergent %.0f ml.", prepMl);
        } else {
            System.out.printf("Not enough detergent, %.0f ml. more necessary!", Math.abs(prepMl));
        }
    }
}
