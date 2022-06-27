package Exercise_03;

import java.util.Arrays;
import java.util.Scanner;

public class ZigZagArrays_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] firstArr = new int[n];
        int[] secondArr = new int[n];

        for (int i = 1; i <= n; i++) {
            String numbers = scanner.nextLine();
            int firstNumber = Integer.parseInt(numbers.split(" ")[0]);
            int secondNumber = Integer.parseInt(numbers.split(" ")[1]);
            if (i % 2 != 0) {
                firstArr[i - 1] = firstNumber;
                secondArr[i - 1] = secondNumber;
            } else {
                secondArr[i - 1] = firstNumber;
                firstArr[i - 1] = secondNumber;
            }
        }
        for (int number : firstArr) {
            System.out.printf("%d ", number);
        }
        System.out.println();
        for (int number : secondArr) {
            System.out.printf("%d ", number);
        }
    }
}
