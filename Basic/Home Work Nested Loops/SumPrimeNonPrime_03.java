package Exercises_06;

import javax.swing.plaf.IconUIResource;
import java.util.Scanner;

public class SumPrimeNonPrime_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int primeSum = 0;
        int nonPrimeSum = 0;

        while (!input.equals("stop")) {
            int number = Integer.parseInt(input);
            if (number < 0) {
                System.out.println("Number is negative.");
                input = scanner.nextLine();
                continue;
            }
            boolean flag = true;
            for (int i = 2; i <= number - 1; i++) {
                if (number % i == 0) {
                    nonPrimeSum += number;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                primeSum += number;
            }
            input = scanner.nextLine();
        }
        System.out.printf("Sum of all prime numbers is: %d\n", primeSum);
        System.out.printf("Sum of all non prime numbers is: %d", nonPrimeSum);

    }
}
