package Lab_03;

import java.util.Arrays;
import java.util.Scanner;

public class LargestNumbers_09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .sorted(
                        (left, right) -> right.compareTo(left)
                ).limit(3)
                .forEach(n -> System.out.printf("%d ", n));
    }
}
