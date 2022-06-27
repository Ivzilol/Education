package Exercises_04;

import java.util.Scanner;

public class TrekkingMania_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberGroups = Integer.parseInt(scanner.nextLine());


        int musala = 0;
        int monblan = 0;
        int kilimanjaro = 0;
        int k2 = 0;
        int everest = 0;
        double totalPeople = 0;

        for (int i = 1; i <= numberGroups ; i++) {
            int numberPeople = Integer.parseInt(scanner.nextLine());
            totalPeople  += numberPeople;

            if (numberPeople <= 5){
                musala += numberPeople;
            }else if (numberPeople <= 12){
                monblan += numberPeople;
            }else if (numberPeople <= 25){
                kilimanjaro += numberPeople;
            }else if (numberPeople <= 40){
                k2 += numberPeople;
            }else {
                everest += numberPeople;
            }

        }
        System.out.printf("%.2f%%%n", musala / totalPeople * 100);
        System.out.printf("%.2f%%%n", monblan / totalPeople * 100);
        System.out.printf("%.2f%%%n", kilimanjaro / totalPeople * 100);
        System.out.printf("%.2f%%%n", k2 / totalPeople * 100);
        System.out.printf("%.2f%%%n", everest / totalPeople * 100);
    }
}
