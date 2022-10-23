package Exam_01;

import java.util.Scanner;

public class TheHuntingGames_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double days = Double.parseDouble(scanner.nextLine());
        double numberPlayers = Double.parseDouble(scanner.nextLine());
        double groupEnergy = Double.parseDouble(scanner.nextLine());
        double waterPerDay = Double.parseDouble(scanner.nextLine());
        double foodPerDay = Double.parseDouble(scanner.nextLine());

        double totalWater = days * numberPlayers * waterPerDay;
        double totalFood = days * numberPlayers * foodPerDay;


        for (int i = 1; i <= days; i++) {
            double lostEnergyPerDay = Double.parseDouble(scanner.nextLine());
            groupEnergy -= lostEnergyPerDay;
            if (groupEnergy <= 0){
                System.out.printf
                        ("You will run out of energy. You will be left with %.2f food and %.2f water.",
                                totalFood, totalWater);
                return;
            }
            if (i % 2 == 0){
                groupEnergy += groupEnergy * 0.05;
                totalWater -= totalWater * 0.30;
            }
            if (i % 3 == 0){
                totalFood -= totalFood / numberPlayers;
                groupEnergy += groupEnergy * 0.10;
            }
        }
        if (groupEnergy > 0){
            System.out.printf("You are ready for the quest. You will be left with - %.2f energy!", groupEnergy);
        }
    }
}
