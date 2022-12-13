package ExamPreparetion_01;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class PastryShop_01_08 {

    private static final int BISCUIT = 25;
    private static final int CAKE = 50;
    private static final int PASTRY = 75;
    private static final int PIE = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputLiquids = scanner.nextLine();
        String inputIngredients = scanner.nextLine();

        ArrayDeque<Integer> liquids = new ArrayDeque<>();
        ArrayDeque<Integer> ingredients = new ArrayDeque<>();

        Arrays.stream(inputLiquids.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(liquids::offer);
        Arrays.stream(inputIngredients.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(ingredients::push);
        Map<String, Integer> finalDelicacies = new LinkedHashMap<>();
        finalDelicacies.put("Biscuit", 0);
        finalDelicacies.put("Cake", 0);
        finalDelicacies.put("Pie", 0);
        finalDelicacies.put("Pastry", 0);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int currentLiquids = liquids.pop();
            int currentIngredients = ingredients.pop();
            int sumLiquidsAndIngredients = currentLiquids + currentIngredients;
            if (sumLiquidsAndIngredients == BISCUIT) {
                int currentBiscuit = finalDelicacies.get("Biscuit");
                finalDelicacies.put("Biscuit", currentBiscuit + 1);
            } else if (sumLiquidsAndIngredients == CAKE) {
                int currentCake = finalDelicacies.get("Cake");
                finalDelicacies.put("Cake", currentCake + 1);
            } else if (sumLiquidsAndIngredients == PASTRY) {
                int currentPie = finalDelicacies.get("Pie");
                finalDelicacies.put("Pie", currentPie + 1);
            } else if (sumLiquidsAndIngredients == PIE) {
                int currentPastry = finalDelicacies.get("Pastry");
                finalDelicacies.put("Pastry", currentPastry + 1);
            } else {
                currentIngredients += 3;
                ingredients.push(currentIngredients);
            }
        }

        if (finalDelicacies.get("Biscuit") > 0 && finalDelicacies.get("Cake") > 0
                && finalDelicacies.get("Pastry") > 0 && finalDelicacies.get("Pie") > 0) {
            System.out.println("Great! You succeeded in cooking all the food!");
        } else {
            System.out.println("What a pity! You didn't have enough materials to cook everything.");
        }
        if (!liquids.isEmpty()) {
            System.out.print("Liquids left: ");
            for (int index = 0; index < liquids.size(); index++) {
                int number = liquids.peek();
                if (liquids.size() > 1) {
                    System.out.print(number + ", ");
                    liquids.pop();
                }
            }
            System.out.print(liquids.pop());
            System.out.println();
        } else {
            System.out.println("Liquids left: none");
        }
        if (!ingredients.isEmpty()) {
            System.out.print("Ingredients left: ");
            for (int index = 0; index < ingredients.size(); index++) {
                int number = ingredients.peek();
                if (ingredients.size() > 1) {
                    System.out.print(number + ", ");
                    ingredients.pop();
                }
            }
            System.out.print(ingredients.pop());
            System.out.println();
        } else {
            System.out.println("Ingredients left: none");
        }

        for (Map.Entry<String, Integer> entry : finalDelicacies.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }
}
