import java.util.Scanner;

public class FactorialNumber {
    private static int factorialOnNumber(int number) {
        int result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        int factorial = factorialOnNumber(number);

        System.out.println(factorial);

    }
}
