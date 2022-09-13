package Lab_05;

import java.util.*;
import java.util.stream.Collectors;

public class SortEvenNumbers_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //int
        //filter odd numbers
        //convert String
        String[] stringNumbers = scanner.nextLine().split(",\\s+");
        List<String> evenNumbers = Arrays.stream(stringNumbers)
                .map(Integer::parseInt)
                .filter(e -> e % 2 == 0)
                .map(Object::toString)
                .collect(Collectors.toList());
        System.out.println(String.join(", ", evenNumbers));
        List<String> evenNumbersSorted = Arrays.stream(stringNumbers)
                .map(Integer::parseInt)
                .filter(e -> e % 2 == 0)
                .sorted((left, right) -> left.compareTo(right))
                .map(Object::toString)
                .collect(Collectors.toList());
        System.out.println(String.join(", ", evenNumbersSorted));
    }
}
