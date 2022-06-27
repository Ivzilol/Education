package Lab_03;

import java.util.Arrays;
import java.util.Scanner;

public class SumEvenNumbers_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int[] numbersArr = Arrays
                .stream(input.split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int sum = 0;
        for (int i = 0; i < numbersArr.length; i++) {
            int currentNum = numbersArr[i];
            if (currentNum % 2 == 0) {
                sum += currentNum;
            }
        }
        System.out.println(sum);
    }
}
