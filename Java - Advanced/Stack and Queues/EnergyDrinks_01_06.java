package ExamPreparetion_01;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class EnergyDrinks_01_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputCaffeine = scanner.nextLine();
        String inputEnergyDrinks = scanner.nextLine();
        ArrayDeque<Integer> caffeine = new ArrayDeque<>();
        ArrayDeque<Integer> energyDrinks = new ArrayDeque<>();

        Arrays.stream(inputCaffeine.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(caffeine::push);
        Arrays.stream(inputEnergyDrinks.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(energyDrinks::offer);
        int limitStamatCaffeine = 0;
        while (!caffeine.isEmpty() && !energyDrinks.isEmpty()) {
            int currentCaffeine = caffeine.peek();
            int currentEnergyDrink = energyDrinks.peek();
            int caffeineInDrink = currentCaffeine * currentEnergyDrink;
            limitStamatCaffeine += caffeineInDrink;
            if (limitStamatCaffeine <= 300) {
                caffeine.poll();
                energyDrinks.pop();
            }
            if (limitStamatCaffeine > 300) {
                caffeine.poll();
                energyDrinks.pop();
                energyDrinks.offer(currentEnergyDrink);
                limitStamatCaffeine = limitStamatCaffeine - caffeineInDrink - 30;
                if (limitStamatCaffeine < 0) {
                    limitStamatCaffeine = 0;
                }
            }
        }
        if (!energyDrinks.isEmpty()) {
            System.out.print("Drinks left: ");
            for (int index = 0; index < energyDrinks.size(); index++) {
                int number = energyDrinks.peek();
                if (energyDrinks.size() > 1) {
                    System.out.print(number + ", ");
                    energyDrinks.pop();
                }
            }
            System.out.print(energyDrinks.pop());
            System.out.println();
        } else {
            System.out.println("At least Stamat wasn't exceeding the maximum caffeine.");
        }
        System.out.printf("Stamat is going to sleep with %d mg caffeine.", limitStamatCaffeine);
    }
}
