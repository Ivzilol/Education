package Traning04;

import java.util.Scanner;

public class EqualPairs_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int firstSum = 0;
        int secondSum = 0;

        int difference = 0;

        boolean check = true;


        for (int i = 1; i <= n; i++) {
            int numA = Integer.parseInt(scanner.nextLine());
            int numB = Integer.parseInt(scanner.nextLine());
            if (i % 2 != 0) {
                firstSum = numA + numB;
            } else {
                secondSum = numA + numB;
            }
            if (firstSum == secondSum || n < 2) {
                check = true;
            } else {
                difference = Math.abs(secondSum - firstSum);
                check = false;
            }

        }
        if (check) {
            System.out.printf("Yes, value=%d", firstSum);
        } else {
            System.out.printf("No, maxdiff=%d", difference);
        }


    }
}

