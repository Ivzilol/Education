package Lab_04;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MathPower_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double firstNumber = Double.parseDouble(scanner.nextLine());
        double secondNumber = Double.parseDouble(scanner.nextLine());
        double result = powResult(firstNumber, secondNumber);

        DecimalFormat dF = new DecimalFormat("0.####");
        System.out.println(dF.format(result));
    }

    public static double powResult(double firstNumber, double secondNumber) {
        double result = 1;
        for (int i = 1; i <= secondNumber; i++) {
            result = result * firstNumber;
        }
        return result;
    }
}
