package Exercises_04;

import java.util.Scanner;

public class HalfSumElement_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int maxValue = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            if ( maxValue < number){
                maxValue = number;
            }
            sum += number;


        }
        int sumWithoutMaxNumber = sum - maxValue;
        if ( sumWithoutMaxNumber == maxValue){
            System.out.println("Yes ");
            System.out.printf("Sum = %d", maxValue);
        }else {
            System.out.println("No");
            System.out.printf("Diff = %d ", Math.abs(sumWithoutMaxNumber - maxValue));
        }
    }
}
