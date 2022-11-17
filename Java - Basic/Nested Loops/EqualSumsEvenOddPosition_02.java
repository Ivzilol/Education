package Exercises_06;

import java.util.Scanner;

public class EqualSumsEvenOddPosition_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int firstNumber = Integer.parseInt(scanner.nextLine());
        int secondNumber = Integer.parseInt(scanner.nextLine());

        for (int i = firstNumber; i <= secondNumber ; i++) {
            int currentNumber = i;
            int evenSum = 0;
            int oddSum = 0;
            for (int j = 6; j >= 1 ; j--) {
                int digit = currentNumber % 10;
                if (j % 2 == 0){
                    evenSum += digit;
                }else {
                    oddSum += digit;
                }
                currentNumber /= 10;

            }
            if (evenSum == oddSum){
                System.out.print(i + " ");
            }


        }

    }
}
