package Lab_04;

import java.util.Scanner;

public class Orders_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String product = scanner.nextLine();
        int numberProducts = Integer.parseInt(scanner.nextLine());
        double price = 0;
        switch (product) {
            case "coffee":
                coffee(product, numberProducts, price);
                break;
            case "water":
                water(product, numberProducts, price);
                break;
            case "coke":
                coke(product, numberProducts, price);
                break;
            case "snacks":
                snacks(product, numberProducts, price);
                break;
        }
    }

    public static void coffee(String product, int numberProducts, double price) {
        price = numberProducts * 1.50;
        System.out.printf("%.2f", price);
    }

    public static void water(String product, int numberProducts, double price) {
        price = numberProducts * 1.00;
        System.out.printf("%.2f", price);
    }

    public static void coke(String product, int numberProducts, double price) {
        price = numberProducts * 1.40;
        System.out.printf("%.2f", price);
    }

    public static void snacks(String product, int numberProducts, double price) {
        price = numberProducts * 2.00;
        System.out.printf("%.2f", price);
    }
}
