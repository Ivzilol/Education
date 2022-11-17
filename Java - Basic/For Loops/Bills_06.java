package Traning04;

import java.util.Scanner;

public class Bills_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int months = Integer.parseInt(scanner.nextLine());
        double billElectricity = 0;
        double billWater = 0;
        double billInternet = 0;
        double billOther = 0;
        double bill20Percent = 0;
        double average = 0;

        for (int i = 1; i <= months; i++) {
            double electricity = Double.parseDouble(scanner.nextLine());
            billElectricity += electricity;
            billWater += 20;
            billInternet += 15;

        }
        bill20Percent = (billElectricity + billWater + billInternet) * 0.20;
        billOther = billElectricity + billInternet + billWater + bill20Percent;
        average = (billElectricity + billWater + billInternet + billOther) / months;
        System.out.printf("Electricity: %.2f lv%n", billElectricity);
        System.out.printf("Water: %.2f lv%n", billWater);
        System.out.printf("Internet: %.2f lv%n", billInternet);
        System.out.printf("Other: %.2f lv%n", billOther);
        System.out.printf("Average: %.2f lv", average);
    }
}
