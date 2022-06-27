package Exercises_06;

import java.util.Scanner;

public class CinemaTickets_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String movie = scanner.nextLine();
        int countKid = 0;
        int countStandart = 0;
        int countStudent = 0;
        int allCounter = 0;


        while (!movie.equals("Finish")) {
            int freeSeats = Integer.parseInt(scanner.nextLine());
            int currentCounter = 0;

            String input = scanner.nextLine();
            while (!input.equals("End")) {
                allCounter++;
                currentCounter++;
                switch (input) {
                    case "standard":
                        countStandart++;
                        break;
                    case "student":
                        countStudent++;
                        break;
                    case "kid":
                        countKid++;
                        break;

                }
                if (currentCounter == freeSeats) {
                    break;
                }

                input = scanner.nextLine();
            }
            System.out.printf("%s - %.2f%% full.\n", movie, currentCounter * 1.00 / freeSeats * 100);

            movie = scanner.nextLine();
        }
        System.out.printf("Total tickets: %d\n", allCounter);
        System.out.printf("%.2f%% student tickets.\n", countStudent * 1.00 / allCounter * 100);
        System.out.printf("%.2f%% standard tickets.\n", countStandart * 1.00 / allCounter * 100);
        System.out.printf("%.2f%% kids tickets.", countKid * 1.00 / allCounter * 100);

    }
}
