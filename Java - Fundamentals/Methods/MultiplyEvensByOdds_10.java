package Lab_04;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Spliterator;

public class MultiplyEvensByOdds_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] n = Arrays.stream(scanner.nextLine().split(""))
                .filter(s -> s.matches("\\d")) // skip all but "0", "1", ... , "9"
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(multiply(n));
    }

    private static int multiply(int[] a) {
        int even = 0;
        int odd = 0;
        for (int j : a) {
            if (j % 2 == 0) {
                even += j;
            } else {
                odd += j;
            }
        }
        return even * odd;
    }
}
