package Lab_05;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class FilterbyAge_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> people = new LinkedHashMap<>();

        int peopleCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < peopleCount; i++) {
            String input = scanner.nextLine();
            String peopleInput = input.split(",\\s+")[0];
            Integer age = Integer.parseInt(input.split(",\\s+")[1]);
            people.put(peopleInput, age);
        }
        String comparison = scanner.nextLine();
        int ageLimit = Integer.parseInt(scanner.nextLine());
        String typePrint = scanner.nextLine();
        BiPredicate<Integer, Integer> filterPredicate;
        if (comparison.equals("younger")) {
            filterPredicate = (personAge, age) -> personAge <= age;
        } else {
            filterPredicate = (personAge, age) -> personAge >= age;
        }
        Consumer<Map.Entry<String, Integer>> printConsumer;
        if (typePrint.equals("age")) {
            printConsumer = person -> System.out.println(person.getValue());
        } else if (typePrint.equals("name")) {
            printConsumer = person -> System.out.println(person.getKey());
        } else {
            printConsumer = person -> System.out.printf("%s - %d\n", person.getKey(), person.getValue());
        }

        people.entrySet()
                .stream()
                .filter(person -> filterPredicate.test(person.getValue(), ageLimit))
                .forEach(printConsumer);
    }
}
