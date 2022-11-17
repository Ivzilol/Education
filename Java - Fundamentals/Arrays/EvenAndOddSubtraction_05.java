package Lab_03;

import java.util.Arrays;
import java.util.Scanner;

public class EvenAndOddSubtraction_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int evenSum = 0;
        int oddSum = 0;
        for (int item : numbers) {
            if (item % 2 == 0) {
                evenSum += item;
            } else {
                oddSum += item;
            }
        }
        System.out.printf("%d", evenSum - oddSum);
    }
}
