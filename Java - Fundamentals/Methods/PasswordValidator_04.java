package Exercise_04;

import java.util.Scanner;

public class PasswordValidator_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String pass = scanner.nextLine();

        if (!validLength(pass)) {
            System.out.println("Password must be between 6 and 10 characters");
        }
        if (!validContent(pass)) {
            System.out.println("Password must consist only of letters and digits");
        }
        if (!validNumbers(pass)) {
            System.out.println("Password must have at least 2 digits");
        }
        if (validLength(pass) && validContent(pass) && validNumbers(pass)) {
            System.out.println("Password is valid");
        }
    }

    private static boolean validLength(String pass) {
        return pass.length() >= 6 && pass.length() <= 10;
    }

    private static boolean validContent(String pass) {
        for (char symbol : pass.toCharArray()) {
            if (!Character.isLetterOrDigit(symbol)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validNumbers(String pass) {
        int countDigits = 0;
        for (char symbol : pass.toCharArray()) {
            if (Character.isDigit(symbol)) {
                countDigits++;
            }
        }
        return countDigits >= 2;
    }
}
