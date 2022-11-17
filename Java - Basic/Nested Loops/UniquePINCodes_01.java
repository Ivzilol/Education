package Traning06;

import java.util.Scanner;

public class UniquePINCodes_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number1 = Integer.parseInt(scanner.nextLine());
        int number2 = Integer.parseInt(scanner.nextLine());
        int number3 = Integer.parseInt(scanner.nextLine());

        for (int i = 2; i <= number1 ; i+= 2) {
            for (int j = 2; j <= number2  ; j++) {
                for (int k = 2; k <= number3 ; k+= 2) {
                    if (j == 2 || j == 3 || j == 5 || j == 7){
                        System.out.printf("%d %d %d\n", i, j, k);
                    }
                }
            }
        }
    }
}
