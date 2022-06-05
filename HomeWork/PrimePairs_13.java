package Traning06;

import java.util.Scanner;

public class PrimePairs_13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int initialValueFirstCouple = Integer.parseInt(scanner.nextLine());
        int initialValueSecondCouple = Integer.parseInt(scanner.nextLine());
        int finalValueFirstCouple = Integer.parseInt(scanner.nextLine());
        int finalValueSecondCouple = Integer.parseInt(scanner.nextLine());
        boolean first = true;
        boolean second = true;
        for (int i = initialValueFirstCouple; i <= initialValueFirstCouple + finalValueFirstCouple ; i++) {
            first = true;
            for (int j = 2; j <= i - 1 ; j++) {
                if (i % j == 0) {
                    first = false;
                    break;
                }
            }
            if (first){
                for (int k = initialValueSecondCouple; k <= initialValueSecondCouple + finalValueSecondCouple ; k++) {
                    second = true;
                    for (int l = 2; l <= k - 1 ; l++) {
                        if (k % l == 0) {
                            second = false;
                            break;
                        }
                    }
                    if (second){
                        System.out.printf("%d%d \n", i, k);
                    }
                }
            }
        }
    }
}
