package Exercise_03;

import java.util.Arrays;
import java.util.Scanner;

public class TopIntegers_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numArr = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i <= numArr.length - 1; i++) {
            int number = 0;
            int rightNumber = 0;
            for (int j = 0; j <= i; j++) {
                number = numArr[j];
            }
            for (int k = i + 1; k <= numArr.length - 1; k++) {
                if (numArr[k] >= rightNumber) {
                    rightNumber = numArr[k];
                }
            }
            if (number >= rightNumber) {
                System.out.printf("%d ", number);
            }
        }
    }
}
