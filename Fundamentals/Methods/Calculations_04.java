package Lab_04;

import java.util.Scanner;

public class Calculations_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String actions = scanner.nextLine();
        int firstNumber = Integer.parseInt(scanner.nextLine());
        int secondNumber = Integer.parseInt(scanner.nextLine());
        int result = 0;
        switch (actions) {
            case "add":
                add(firstNumber, secondNumber, result);
                break;
            case "multiply":
                multiply(firstNumber, secondNumber, result);
                break;
            case "subtract":
                subtract(firstNumber, secondNumber, result);
                break;
            case "divide":
                divide(firstNumber, secondNumber, result);
                break;
        }
    }

    public static void add(int firstNumber, int secondNumber, int result) {
        result = firstNumber + secondNumber;
        System.out.print(result);
    }

    public static void multiply(int firstNumber, int secondNumber, int result) {
        result = firstNumber * secondNumber;
        System.out.print(result);
    }

    public static void subtract(int firstNumber, int secondNumber, int result) {
        result = firstNumber - secondNumber;
        System.out.print(result);
    }

    public static void divide(int firstNumber, int secondNumber, int result) {
        result = firstNumber / secondNumber;
        System.out.print(result);
    }
}
