package Lab_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SumNumbers_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Function<String, Integer> parser = e -> Integer.parseInt(e);
        String[] input = scanner.nextLine().split(",\\s+");
        List<Integer> numbers = Arrays.stream(input)
                .map(parser)
                .collect(Collectors.toList());
        System.out.printf("Count = %d\n", numbers.size());
        int sum = Arrays.stream(input).mapToInt(e -> Integer.parseInt(e)).sum();
        System.out.println("Sum = " + sum);
    }
}
