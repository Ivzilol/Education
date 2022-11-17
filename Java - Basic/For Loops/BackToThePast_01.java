package Traning04;

import java.util.Scanner;

public class BackToThePast_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double inheritedMoney = Double.parseDouble(scanner.nextLine());
        int yearsToLive = Integer.parseInt(scanner.nextLine());
        double moneySpend = 0;
        int years = 18;

        for (int i = 1800; i <= yearsToLive ; i++) {

            if (i % 2 == 0){
                moneySpend += 12000;
            }else{
                moneySpend += 12000 + (50 * years);
            }
            years ++;
        }
        if (moneySpend <= inheritedMoney){
            System.out.printf("Yes! He will live a carefree life and will have %.2f dollars left.", (inheritedMoney - moneySpend));
        }else {
            System.out.printf("He will need %.2f dollars to survive.", moneySpend - inheritedMoney);
        }
    }
}
