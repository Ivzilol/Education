package ExamPreparetion_01;

import java.util.*;

public class AutumnCocktails_01_01 {
    private static final int PEAR_SOUR = 150;
    private static final int THE_HARVEST = 250;
    private static final int APPLE_HINNI = 300;
    private static final int HIGH_FASHION = 400;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> cocktails = new TreeMap<>();
        cocktails.put("Pear Sour", 0);
        cocktails.put("The Harvest", 0);
        cocktails.put("Apple Hinny", 0);
        cocktails.put("High Fashion", 0);
        String[] tokens = scanner.nextLine().split("\\s+");
        ArrayDeque<Integer> ingredients = new ArrayDeque<>();


        for (String token : tokens) {
            ingredients.offer(Integer.parseInt(token));
        }
        tokens = scanner.nextLine().split("\\s+");
        ArrayDeque<Integer> freshness = new ArrayDeque<>();
        for (String token : tokens) {
            freshness.push(Integer.parseInt(token));
        }
        while (!ingredients.isEmpty() && !freshness.isEmpty()) {
            Integer firstIngredients = ingredients.poll();
            if (firstIngredients == 0) {
                continue;
            }
            Integer lastFreshness = freshness.pop();
            int totalLevel = firstIngredients * lastFreshness;
            switch (totalLevel) {
                case PEAR_SOUR:
                    int currentPearSours = cocktails.get("Pear Sour");
                    cocktails.put("Pear Sour", currentPearSours + 1);
                    break;
                case THE_HARVEST:
                    int currentTheHarvest = cocktails.get("The Harvest");
                    cocktails.put("The Harvest", currentTheHarvest + 1);
                    break;
                case APPLE_HINNI:
                    int currentAppleHinny = cocktails.get("Apple Hinny");
                    cocktails.put("Apple Hinny", currentAppleHinny + 1);
                    break;
                case HIGH_FASHION:
                    int currentHighFashion = cocktails.get("High Fashion");
                    cocktails.put("High Fashion", currentHighFashion + 1);
                    break;
                default:
                    firstIngredients += 5;
                    ingredients.offer(firstIngredients);
                    break;
            }
        }
        if (cocktails.get("Pear Sour") > 0
                && cocktails.get("The Harvest") > 0
                && cocktails.get("Apple Hinny") > 0
                && cocktails.get("High Fashion") > 0) {
            System.out.println("It's party time! The cocktails are ready!");
        } else {
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");

        }
        if (!ingredients.isEmpty()) {
            int leftOverIngredients = ingredients.stream().mapToInt(e -> e).sum();
            System.out.printf("Ingredients left: %d\n", leftOverIngredients);
        }
        for (Map.Entry<String, Integer> entry : cocktails.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.printf("# %s --> %d\n", entry.getKey(), entry.getValue());
            }
        }
    }
}
