package Exam_preparation_01;

import java.util.Scanner;

public class BlackFlag_01_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double days = Double.parseDouble(scanner.nextLine());
        double dailyPlunder = Double.parseDouble(scanner.nextLine());
        double expectedPlunder = Double.parseDouble(scanner.nextLine());
        double allPlunder = 0;

        for (int index = 1; index <= days; index++) {
            allPlunder += dailyPlunder;
            if (index % 3 == 0) {
                allPlunder += dailyPlunder * 0.50;
            }
            if (index % 5 == 0) {
                allPlunder -= allPlunder * 0.30;
            }
        }
        if (allPlunder >= expectedPlunder) {
            System.out.printf("Ahoy! %.2f plunder gained.", allPlunder);
        } else {
            System.out.printf("Collected only %.2f%% of the plunder.", allPlunder / expectedPlunder * 100);
        }
    }
}
