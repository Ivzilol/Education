package ExamPreparetion_01;

import java.util.*;

public class Cooking_01_12 {

    private static final int BREAD = 25;
    private static final int CAKE = 50;
    private static final int PASTRY = 75;
    private static final int FRUIT_PIE = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputLiquids = scanner.nextLine();
        String inputIngredients = scanner.nextLine();

        ArrayDeque<Integer> liquids =  new ArrayDeque<>();
        ArrayDeque<Integer> ingredients =  new ArrayDeque<>();

        Arrays.stream(inputLiquids.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(liquids::offer);
        Arrays.stream(inputIngredients.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(ingredients::push);

        Map<String, Integer> finalDelicacies = new TreeMap<>();
        finalDelicacies.put("Bread", 0);
        finalDelicacies.put("Cake", 0);
        finalDelicacies.put("Pastry", 0);
        finalDelicacies.put("Fruit Pie", 0);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int currentLiquids = liquids.peek();
            int currentIngredients = ingredients.peek();
            int sum = currentLiquids + currentIngredients;

            if (sum == BREAD) {
                liquids.poll();
                ingredients.pop();
                finalDelicacies.put("Bread", finalDelicacies.get("Bread") + 1);
            } else if (sum == CAKE) {
                liquids.poll();
                ingredients.pop();
                finalDelicacies.put("Cake", finalDelicacies.get("Cake") + 1);
            } else if (sum == PASTRY) {
                liquids.poll();
                ingredients.pop();
                finalDelicacies.put("Pastry", finalDelicacies.get("Pastry") + 1);
            } else if (sum == FRUIT_PIE) {
                liquids.poll();
                ingredients.pop();
                finalDelicacies.put("Fruit Pie", finalDelicacies.get("Fruit Pie") + 1);
            } else {
                liquids.poll();
                currentIngredients += 3;
                ingredients.pop();
                ingredients.push(currentIngredients);
            }
        }

        if (finalDelicacies.get("Bread") > 0 && finalDelicacies.get("Cake") > 0 &&
                finalDelicacies.get("Pastry") > 0 && finalDelicacies.get("Fruit Pie") > 0) {
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to cook everything.");
        }
        if (liquids.isEmpty()) {
            System.out.println("Liquids left: none");
        } else {
            printLiquids(liquids);
        }

        if (ingredients.isEmpty()) {
            System.out.println("Ingredients left: none");
        } else {
            printIngredients(ingredients);
        }

        for (Map.Entry<String, Integer> entry : finalDelicacies.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }

    private static void printLiquids(ArrayDeque<Integer> liquids) {
        System.out.print("Liquids left: ");
        for (Integer currentLiquids : liquids) {
            if (liquids.size() != 1) {
                System.out.print(currentLiquids + ", ");
                liquids.poll();
            } else {
                System.out.print(currentLiquids);
            }
        }
        System.out.println();
    }

    private static void printIngredients(ArrayDeque<Integer> ingredients) {
        System.out.print("Ingredients left: ");
        for (Integer currentIngredients : ingredients) {
            if (ingredients.size() != 1) {
                System.out.print(currentIngredients + ", ");
                ingredients.poll();
            } else {
                System.out.print(currentIngredients);
            }
        }
        System.out.println();
    }
}
