import java.util.Scanner;

public class FindGCD {

    private static int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return findGCD(b, a % b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int firstNumber = Integer.parseInt(scanner.nextLine());
        int secondNumber = Integer.parseInt(scanner.nextLine());

        int findCD = findGCD(firstNumber, secondNumber);

        System.out.println(findCD);
    }
}
