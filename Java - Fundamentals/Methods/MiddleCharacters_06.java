package Exercise_04;

import java.util.Scanner;

public class MiddleCharacters_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        middleSymbol(input);
    }

    private static void middleSymbol(String input) {
        if (input.length() % 2 != 0) {
            int middleChar = input.length() / 2;
            System.out.println(input.charAt(middleChar));
        } else {
            int firstMiddle = input.length() / 2 - 1;
            int secondMiddle = input.length() / 2;
            System.out.println(input.charAt(firstMiddle) + "" + input.charAt(secondMiddle));
        }
    }
}
