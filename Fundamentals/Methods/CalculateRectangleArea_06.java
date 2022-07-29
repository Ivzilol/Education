package Lab_04;

import java.util.Scanner;

public class CalculateRectangleArea_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double width = Double.parseDouble(scanner.nextLine());
        double length = Double.parseDouble(scanner.nextLine());
        double area = calculateRectangle(width, length);
        System.out.printf("%.0f", area);
    }

    public static double calculateRectangle(double width, double length) {
        return width * length;
    }
}
