package Exercise_04;

import java.util.Scanner;

public class FactorialDivision_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1 = Integer.parseInt(scanner.nextLine());
        int n2 = Integer.parseInt(scanner.nextLine());

        long fact1 = calculateFactorial(n1);
        long fact2 = calculateFactorial(n2);
        double result = fact1 * 1.00 / fact2;
        System.out.printf("%.2f", result);
    }

    private static long calculateFactorial(int n1) {
        long fact = 1;
        for (int i = 1; i <= n1; i++) {
            fact = fact * i;

        }
        return fact;
    }
}
