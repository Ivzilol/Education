import java.util.Scanner;

public class PalindromeNumber {

    private static int reversNumber(int number) {
        int reverse = 0;
        while (number != 0) {
            int rem = number % 10;
            reverse = reverse * 10 + rem;
            number = number / 10;
        }
        return reverse;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());

        int reverseNumber = reversNumber(number);

        if (number == reverseNumber) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
