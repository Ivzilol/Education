package Traning04;

import java.util.Scanner;

public class Logistics_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cargoNumber = Integer.parseInt(scanner.nextLine());
        double allCargo = 0;
        double cargoTo3Tons = 0;
        double cargoTo11Tons = 0;
        double cargoOver12Tons = 0;

        for (int i = 1; i <= cargoNumber ; i++) {
            int cargoTons = Integer.parseInt(scanner.nextLine());
            allCargo += cargoTons;
            if (cargoTons <= 3){
                cargoTo3Tons += cargoTons;
            }else if (cargoTons <= 11){
                cargoTo11Tons += cargoTons;
            }else {
                cargoOver12Tons += cargoTons;
            }

        }
        double tons3 = cargoTo3Tons * 200;
        double tons11 = cargoTo11Tons * 175;
        double tons12 = cargoOver12Tons * 120;

        double cargoAverage = (tons3 + tons11 + tons12)/ allCargo;

        System.out.printf("%.2f%n", cargoAverage);
        System.out.printf("%.2f%%%n", (cargoTo3Tons / allCargo * 100));
        System.out.printf("%.2f%%%n", (cargoTo11Tons / allCargo * 100));
        System.out.printf("%.2f%%", (cargoOver12Tons / allCargo * 100));
    }
}
