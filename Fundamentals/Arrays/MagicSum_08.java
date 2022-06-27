package Exercise_03;

import java.util.Arrays;
import java.util.Scanner;

public class MagicSum_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <= array.length - 1; i++) {
            for (int j = i + 1; j <= array.length - 1; j++) {
                if (array[i] + array[j] == n){
                    System.out.printf("%d %d\n", array[i], array[j]);
                }
            }
        }
    }
}
