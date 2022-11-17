package Traning04;

import java.util.Scanner;

public class FootballLeague_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int capacity = Integer.parseInt(scanner.nextLine());
        int numberFans = Integer.parseInt(scanner.nextLine());
        double fansA = 0;
        double fansB = 0;
        double fansV = 0;
        double fansG = 0;


        for (int i = 1; i <= numberFans; i++) {
            String sector = scanner.nextLine();
            switch (sector) {
                case "A":
                    fansA += 1;
                    break;
                case "B":
                    fansB += 1;
                    break;
                case "V":
                    fansV += 1;
                    break;
                case "G":
                    fansG += 1;
                    break;

            }
        }
        System.out.printf("%.2f%%%n", (fansA / numberFans * 100));
        System.out.printf("%.2f%%%n", (fansB / numberFans * 100));
        System.out.printf("%.2f%%%n", (fansV / numberFans * 100));
        System.out.printf("%.2f%%%n", (fansG / numberFans * 100));
        System.out.printf("%.2f%%", (numberFans * 1.00 / capacity) * 100);
    }
}
