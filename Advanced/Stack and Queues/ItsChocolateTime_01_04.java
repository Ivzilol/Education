package ExamPreparetion_01;

import java.util.*;

public class ItsChocolateTime_01_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> chocolates = new TreeMap<>();
        chocolates.put("Milk Chocolate", 0.0);
        chocolates.put("Dark Chocolate", 0.0);
        chocolates.put("Baking Chocolate", 0.0);

        String milkInput = scanner.nextLine();
        String cacaoInput = scanner.nextLine();

        ArrayDeque<Double> milk = new ArrayDeque<>();
        ArrayDeque<Double> cacao = new ArrayDeque<>();

        Arrays.stream(milkInput.split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .forEach(milk::offer);
        Arrays.stream(cacaoInput.split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .forEach(cacao::push);
        while (!milk.isEmpty() && !cacao.isEmpty()) {
            double currentMilk = milk.peek();
            double currentCacao = cacao.peek();
            double cacaoPercent = currentCacao / (currentMilk + currentCacao) * 100;
            if (cacaoPercent == 100) {
                double currentBakingChocolate = chocolates.get("Baking Chocolate");
                chocolates.put("Baking Chocolate", currentBakingChocolate + 1);
                milk.pop();
                cacao.poll();
            } else if (cacaoPercent == 50) {
                double currentDarkChocolate = chocolates.get("Dark Chocolate");
                chocolates.put("Dark Chocolate", currentDarkChocolate + 1);
                milk.pop();
                cacao.poll();
            } else if (cacaoPercent == 30) {
                double currentMilkChocolate = chocolates.get("Milk Chocolate");
                chocolates.put("Milk Chocolate", currentMilkChocolate + 1);
                milk.pop();
                cacao.poll();
            } else {
                cacao.poll();
                milk.pop();
                double newMilkQuantity = currentMilk + 10;
                milk.offer(newMilkQuantity);
            }
        }
        if (chocolates.get("Baking Chocolate") > 0
                && chocolates.get("Dark Chocolate") > 0
                && chocolates.get("Milk Chocolate") > 0) {
            System.out.println("It’s a Chocolate Time. All chocolate types are prepared.");
        } else {
            System.out.println("Sorry, but you didn't succeed to prepare all types of chocolates.");
        }
        if (!chocolates.isEmpty()) {
            for (Map.Entry<String, Double> entry : chocolates.entrySet()) {
                if (entry.getValue() > 0) {
                    System.out.printf("# %s --> %.0f\n", entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
