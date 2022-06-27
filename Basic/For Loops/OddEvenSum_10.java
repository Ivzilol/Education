package Lab_4;

import java.util.Scanner;

public class OddEvenSum_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int oddSum = 0;
        int evenSum = 0;
        for (int i = 1; i <= n; i++){
            int number = Integer.parseInt(scanner.nextLine());

            if (i % 2 == 0) {
                evenSum += number;
            }
            else {
                oddSum += number;
            }
        }
        if (evenSum == oddSum){
            System.out.printf("Yes\n" + "Sum = %d", evenSum);
        }
        else {
            System.out.printf("No\n" + "Diff = %d" , Math.abs(evenSum - oddSum));
        }
    }
}

