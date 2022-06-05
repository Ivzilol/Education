package Traning06;

import java.util.Scanner;

public class LettersCombinations_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char firstChar = scanner.nextLine().charAt(0);
        char lastChar = scanner.nextLine().charAt(0);
        char ignoreChar = scanner.nextLine().charAt(0);
        int counter = 0;

        for (char i = firstChar; i <= lastChar; i++) {
            for (char j = firstChar; j <= lastChar; j++) {
                for (char k = firstChar; k <= lastChar; k++) {
                    if (i != ignoreChar && j != ignoreChar && k != ignoreChar) {
                        System.out.print("" + i + j + k + " ");
                        counter++;
                    }
                }
            }
        }
        System.out.println(counter);
    }
}
