package Exercise_03;

import java.util.Arrays;
import java.util.Scanner;

public class MaxSequenceOfEqualElements_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numArr = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int number = 0;
        int countMax = 0;
        int maxNumber = 0;
        for (int i = 0; i < numArr.length - 1; i++) {
            int count = 0;
            for (int j = i; j <= numArr.length - 1; j++) {
                if (numArr[i] == numArr[j]) {
                    count++;
                    number = i;
                    if (count > countMax) {
                        countMax = count;
                        maxNumber = number;
                    }
                } else {
                    break;
                }
            }
        }
        for (int j = 0; j < countMax; j++) {
            System.out.print(numArr[j + maxNumber] + " ");
        }
    }
}
