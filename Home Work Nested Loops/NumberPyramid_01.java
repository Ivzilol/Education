package Exercises_06;

import java.util.Scanner;

public class NumberPyramid_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int currentNumber = 0;
        for (int rows = 1; rows <= n ; rows++) {
            for (int cols = 1; cols <= rows ; cols++) {
                currentNumber++;
                if (currentNumber > n){
                    break;
                }
                System.out.print( currentNumber + " " );

            }
            if (currentNumber > n){
                break;
            }
            System.out.println();

        }

    }
}
