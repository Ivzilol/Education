package Exam_preparation_01;

import java.util.Scanner;

public class CounterStrike_01_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int energy = Integer.parseInt(scanner.nextLine());

        String input = scanner.nextLine();
        int wonBattles = 0;
        while (!input.equals("End of battle")) {
            int attackPower = Integer.parseInt(input);
            if (energy >= attackPower) {
                wonBattles++;
                energy -= attackPower;
            } else {
                System.out.printf("Not enough energy! Game ends with %d won battles and %d energy", wonBattles, energy);
                return;
            }
            if (wonBattles % 3 == 0) {
                energy += wonBattles;
            }
            input = scanner.nextLine();
        }
        System.out.printf("Won battles: %d. Energy left: %d", wonBattles, energy);
    }
}
