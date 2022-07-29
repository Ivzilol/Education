package Lab_04;

import java.util.Scanner;

public class MathOperations_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double firstNumber = Double.parseDouble(scanner.nextLine());
        char symbol = scanner.nextLine().charAt(0);
        double secondNumber = Double.parseDouble(scanner.nextLine());
        double result = 0;
        switch (symbol) {
            case '/':
                result = divide(firstNumber, secondNumber);
                break;
            case '*':
                result = multiplication(firstNumber, secondNumber);
                break;
            case '+':
                result = add(firstNumber, secondNumber);
                break;
            case '-':
                result = subtraction(firstNumber, secondNumber);
                break;
        }
        System.out.printf("%.0f", result);
    }

    public static double divide(double firstNumber, double secondNumber) {
        return firstNumber / secondNumber;
    }

    public static double multiplication(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }

    public static double add(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }

    public static double subtraction(double firstNumber, double secondNumber) {
        return firstNumber - secondNumber;
    }
}
