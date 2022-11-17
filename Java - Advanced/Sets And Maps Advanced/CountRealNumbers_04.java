package Lab_03;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CountRealNumbers_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble).toArray();
        Map<Double, Integer> occurrences = new LinkedHashMap<>();
        for (int index = 0; index < numbers.length; index++) {
            if (occurrences.containsKey(numbers[index])) {
                int currentNumber = occurrences.get(numbers[index]);
                currentNumber++;
                occurrences.put(numbers[index], currentNumber);

            } else {
                occurrences.put(numbers[index], 1);
            }
        }
        for (Map.Entry<Double, Integer> entry : occurrences.entrySet()) {
            System.out.printf("%.1f -> %d\n", entry.getKey(), entry.getValue());
        }
    }
}
