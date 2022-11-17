package Lab_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CountUppercaseWords_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Predicate<String> upperCasePredicat = word -> Character.isUpperCase(word.charAt(0));
        Consumer<String> printer = System.out::println;

        List<String> upperCaseWords = Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(upperCasePredicat).toList();

        System.out.println(upperCaseWords.size());
        upperCaseWords.forEach(printer);
    }
}
