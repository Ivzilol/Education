package Lab_06;

import java.math.BigInteger;
import java.util.Scanner;

public class BigFactorial_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        BigInteger bigNumber = new BigInteger(String.valueOf(1));
        for (int index = 1; index <= n; index++) {
            bigNumber = bigNumber.multiply(BigInteger.valueOf(index));
        }
        System.out.println(bigNumber);
    }
}
