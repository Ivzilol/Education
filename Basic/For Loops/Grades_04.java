package Traning04;

import java.util.Scanner;

public class Grades_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberStudents = Integer.parseInt(scanner.nextLine());
        double priceTo3 = 0;
        double priceTo4 = 0;
        double priceTo5 = 0;
        double priceOver5 = 0;
        double averagePrice = 0;


        for (int i = 1; i <= numberStudents; i++) {
            double price = Double.parseDouble(scanner.nextLine());
            averagePrice += price;
            if (price <= 2.99){
                priceTo3 += 1;
            }else if (price <= 3.99){
                priceTo4 += 1;
            }else if (price <= 4.99){
                priceTo5 += 1;
            }else {
                priceOver5 += 1;
            }
        }
        System.out.printf("Top students: %.2f%%%n", (priceOver5 / numberStudents * 100));
        System.out.printf("Between 4.00 and 4.99: %.2f%%%n", (priceTo5 / numberStudents * 100));
        System.out.printf("Between 3.00 and 3.99: %.2f%%%n", (priceTo4 / numberStudents * 100));
        System.out.printf("Fail: %.2f%%%n", (priceTo3 / numberStudents * 100));
        System.out.printf("Average: %.2f", (averagePrice / numberStudents ));

    }
}
