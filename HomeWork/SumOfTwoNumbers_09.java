package Traning06;

import java.util.Scanner;

public class SumOfTwoNumbers_09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number1 = Integer.parseInt(scanner.nextLine());
        int number2 = Integer.parseInt(scanner.nextLine());
        int magicNumber = Integer.parseInt(scanner.nextLine());
        int countCombination = 0;

        for (int i = number1; i <= number2; i++) {
            for (int j = number1; j <= number2; j++) {
                countCombination++;
                if ((i + j) == magicNumber) {
                    System.out.printf("Combination N:%d (%d + %d = %d)", countCombination, i, j, (i + j));
                    return;
                }
            }
        }
        System.out.printf("%d combinations - neither equals %d", countCombination, magicNumber);
    }
}
