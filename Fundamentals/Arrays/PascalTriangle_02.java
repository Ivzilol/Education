package Traning_01;

import java.util.Scanner;

public class PascalTriangle_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int r, i, k, number = 1, j;
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Number of Rows : ");
        r = scan.nextInt();

        for (i = 0; i < r; i++) {
            number = 1;
            for (j = 0; j <= i; j++) {
                System.out.print(number + " ");
                number = number * (i - j) / (j + 1);
            }
            System.out.println();
        }
    }
}
