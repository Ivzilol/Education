package Traning_01;

import java.util.Arrays;
import java.util.Scanner;

public class EncryptSortAndPrintArray_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] allSum = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            String names = scanner.nextLine();
            for (int j = 0; j < names.length(); j++) {
                if (names.charAt(j) == 'a' || names.charAt(j) == 'e' ||
                        names.charAt(j) == 'i' || names.charAt(j) == 'o' || names.charAt(j) == 'u'
                        || names.charAt(j) == 'A' || names.charAt(j) == 'E' || names.charAt(j) == 'I'
                        || names.charAt(j) == 'O' || names.charAt(j) == 'U') {
                    sum += names.charAt(j) * names.length();
                } else {
                    sum += names.charAt(j) / names.length();
                    System.out.println();
                }
            }
            allSum[i] = sum;
            sum = 0;
        }
        Arrays.sort(allSum);
        for (int total : allSum) {
            System.out.println(total);
        }
    }
}
