package Exercises_05;

import java.util.*;
import java.util.logging.SocketHandler;
import java.util.stream.Collectors;

public class CustomComparator_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Function< приема, връща> -> apply
        //Consumer <приема> -> void -> accept
        //Supplier<връща> -> get
        //Predicate<приема> -> връща true / false -> test
        //BiFunction<приема1, приема2, връща> -> apply
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                evenNumbers.add(number);
            } else {
                oddNumbers.add(number);
            }
        }
        evenNumbers.sort(Comparator.naturalOrder());
        oddNumbers.sort(Comparator.naturalOrder());
        evenNumbers.forEach(number -> System.out.print(number + " "));
        oddNumbers.forEach(number -> System.out.print(number + " "));
    }
}
