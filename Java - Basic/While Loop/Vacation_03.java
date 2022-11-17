package Exercises_05;

import java.util.Scanner;

public class Vacation_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double needMoney = Double.parseDouble(scanner.nextLine());
        double savedMoney = Double.parseDouble(scanner.nextLine());
        int countDays = 0;
        int spendDays = 0;
        while (savedMoney < needMoney){
            if (spendDays == 5){
                break;
            }
            String action = scanner.nextLine();
            double amount = Double.parseDouble(scanner.nextLine());
            countDays++;
            if (action.equals("save")){
                savedMoney += amount;
                spendDays = 0;
            }else {
                spendDays++;
                savedMoney -= amount;
                if (savedMoney < 0){
                    savedMoney = 0;
                }
            }

        }
        if (spendDays == 5){
            System.out.println("You can't save the money.");
            System.out.println(countDays);
        }else {
            System.out.printf("You saved the money for %d days.", countDays);
        }


    }
}
