package Lab_07;

import java.util.*;
import java.util.stream.Collectors;

public class CountRealNumbers_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Double> numbersList = Arrays.stream(scanner.nextLine().split(" "))
                .map(Double::parseDouble).collect(Collectors.toList());

        Map<Double, Integer> countNumbers = new TreeMap<>();
        for (double currentNumber : numbersList) {

            countNumbers.putIfAbsent(currentNumber, 0);
            int currentVaule = countNumbers.get(currentNumber);
            countNumbers.put(currentNumber, currentVaule + 1);

          //  Integer currentValue = countNumbers.get(currentNumber);
          //  if (countNumbers.containsKey(currentNumber)) {
          //      countNumbers.put(currentNumber, currentValue + 1);
          //  } else {
          //      countNumbers.put(currentNumber, 1);
          //  }
        }
        for (Map.Entry<Double, Integer> entry : countNumbers.entrySet()) {
            System.out.printf("%.0f -> %d\n", entry.getKey(), entry.getValue());
        }
    }
}
