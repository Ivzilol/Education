package Exercise_03;

import java.util.Arrays;
import java.util.Scanner;

public class EqualSums_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i <= numbers.length - 1; i++) {
            int leftSum = 0;
            int rightSum = 0;
            for (int j = 0; j < i; j++) {
                leftSum += numbers[j];
            }
            for (int k = i + 1; k <= numbers.length - 1; k++) {
                rightSum += numbers[k];
            }
            if (rightSum == leftSum) {
                System.out.println(i);
                return;
            }
        }
        System.out.println("no");
    }
}
