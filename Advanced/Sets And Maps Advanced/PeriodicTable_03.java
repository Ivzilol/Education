package Exercises_03;

import java.util.*;
import java.util.stream.Collectors;

public class PeriodicTable_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Set<String> uniqueElements = new LinkedHashSet<>();

        for (int row = 0; row < n; row++) {
            String[] chemicalElement = scanner.nextLine().split("\\s+");
            //Collections.addAll(Arrays.asList(chemicalElement), uniqueElements);
            uniqueElements.addAll(Arrays.asList(chemicalElement));
        }
        uniqueElements
                .stream()
                .sorted(
                        (left, right) -> {
                            return left.compareTo(right);
                        }
                )
                .forEach(entry -> System.out.print(entry + " "));
    }
}
