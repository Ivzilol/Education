package Traning_01;

import java.util.Arrays;
import java.util.Scanner;

public class KaminoFactory_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cloneSize = Integer.parseInt(scanner.nextLine());
        int[] cloneArr = new int[cloneSize];
        int[] finalArr = new int[cloneSize];
        int countFinalConsecutive = 0;
        int countFinalOne = 0;
        int countArr = 0;
        int countArrFinal = 0;
        int finalIndex = 0;
        String input = scanner.nextLine();
        while (!input.equals("Clone them!")) {
            countArr++;
            cloneArr = Arrays.stream(input.split("!+")).mapToInt(Integer::parseInt).toArray();
            int countConsecutive = 0;
            int countOne = 0;
            int lastNumber = 0;
            int lastIndex = 0;
            for (int index = 0; index <= cloneArr.length - 1; index++) {
                int currentNumber = cloneArr[index];
                if (currentNumber == 1) {
                    countOne++;

                }
                if (currentNumber == 1 && currentNumber == lastNumber) {
                    countConsecutive++;
                    lastIndex = index;
                }
                lastNumber = currentNumber;
            }
            if (countConsecutive > countFinalConsecutive) {
                countFinalConsecutive = countConsecutive;
                countFinalOne = countOne;
                finalIndex = lastIndex;
                finalArr = cloneArr;
                countArrFinal = countArr;
            }
            if (countConsecutive >= countFinalConsecutive && lastIndex < finalIndex) {
                countFinalOne = countOne;
                finalIndex = lastIndex;
                finalArr = cloneArr;
                countArrFinal = countArr;
            }
            if (countConsecutive >= countFinalConsecutive && lastIndex <= finalIndex && countOne > countFinalOne) {
                countFinalOne = countOne;
                finalArr = cloneArr;
                countArrFinal = countArr;
            }
            input = scanner.nextLine();
        }
        System.out.printf("Best DNA sample %d with sum: %d.\n", countArrFinal, countFinalOne);
        for (int number : finalArr) {
            System.out.print(number + " ");
        }
    }
}
