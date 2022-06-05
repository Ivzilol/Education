package Traning06;

import java.util.Scanner;

public class WeddingSeats_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char block = scanner.nextLine().charAt(0);
        int firstSectorRows = Integer.parseInt(scanner.nextLine());
        int numberPlaceOddRow = Integer.parseInt(scanner.nextLine());
        int places = 0;
        int counter = 0;
        int rows = -1;

        for (int i = 'A'; i <= block; i++) {
            rows++;
            for (int j = 1; j <= firstSectorRows + rows; j++) {
                places = 0;
                if (j % 2 == 0) {
                    places += 2;
                }
                for (int k = 'a'; k < 'a' + numberPlaceOddRow + places; k++) {
                    System.out.printf("%c%d%c\n", i, j, k);
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}
