package Exercise_07;

import java.util.*;

public class Orders_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, List<Double>> productsMap = new LinkedHashMap<>();

        while (!input.contains("buy")) {
            String product = input.split(" ")[0];
            double price = Double.parseDouble(input.split(" ")[1]);
            double quantity = Double.parseDouble(input.split(" ")[2]);
            if (!productsMap.containsKey(product)) {
                productsMap.put(product, new ArrayList<>());
                productsMap.get(product).add(price);
                productsMap.get(product).add(quantity);
            } else {
                List<Double> currentPrice = productsMap.get(product);
                double currentQuantity = 0;
                for (int index = 1; index <= currentPrice.size() - 1; index++) {
                    currentQuantity = currentPrice.get(index);
                }
                currentPrice.set(0, price);
                currentPrice.set(1, currentQuantity + quantity);
                productsMap.put(product, currentPrice);
            }
            input = scanner.nextLine();
        }
        productsMap.entrySet().forEach(e -> System.out.printf("%s -> %.2f\n",
                e.getKey(), e.getValue().get(0) * e.getValue().get(1)));
    }
}
