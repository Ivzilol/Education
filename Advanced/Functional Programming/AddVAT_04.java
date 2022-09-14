package Lab_05;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class AddVAT_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] prices = scanner.nextLine().split(",\\s+");
        Function<String, Double> parsToDouble = e -> Double.parseDouble(e);
        UnaryOperator<Double> tax = e -> e * 1.2;
        System.out.println("Prices with VAT:");
        Arrays.stream(prices)
                .map(parsToDouble)
                .map(tax)
                .forEach(e -> System.out.printf("%.2f\n", e));
    }
}
