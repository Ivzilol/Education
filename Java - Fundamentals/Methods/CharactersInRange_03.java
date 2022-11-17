package Exercise_04;

import java.util.Scanner;

public class CharactersInRange_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char char1 = scanner.nextLine().charAt(0);
        char char2 = scanner.nextLine().charAt(0);
        printChar(char1, char2);
    }

    private static void printChar(char char1, char char2) {
        if (char1 < char2) {
            for (char i = (char) (char1 + 1); i < char2; i++) {
                System.out.print(i + " ");
            }

        } else if (char1 > char2) {
            for (char i = (char) (char2 + 1); i < char1; i++) {
                System.out.print(i + " ");
            }
        }
    }
}
