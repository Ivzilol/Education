package Exercises_07.GenericCountMethodDouble_06;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Box<Double> box = new Box<>();
        for (int i = 0; i < n; i++) {
            double numbers = Double.parseDouble(scanner.nextLine());
            box.add(numbers);
        }
        double numbersToCompare = Double.parseDouble(scanner.nextLine());
        System.out.println(box.countGreaterThan(numbersToCompare));
    }
}
