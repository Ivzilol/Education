package Exercise_04;

import java.util.Scanner;

public class AddAndSubtract_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double n1 = Double.parseDouble(scanner.nextLine());
        double n2 = Double.parseDouble(scanner.nextLine());
        double n3 = Double.parseDouble(scanner.nextLine());
        calculation(n1, n2, n3);
    }

    private static void calculation(double n1, double n2, double n3) {
        double sum = (n1 + n2) - n3;
        System.out.printf("%.0f", sum);
    }
}
