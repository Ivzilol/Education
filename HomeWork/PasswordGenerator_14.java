package Traning06;

import java.util.Scanner;

public class PasswordGenerator_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int l = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i < n ; i++) {
            for (int j = 1; j < n ; j++) {
                for (int k = 'a'; k <= 'a' + l - 1 ; k++) {
                    for (int m = 'a'; m <= 'a' + l - 1 ; m++) {
                        for (int o = 2; o <= n ; o++) {
                            if (o > i && o > j){
                                System.out.printf("%d%d%c%c%d ", i, j, k, m, o);
                            }
                        }
                    }
                }
            }
        }
    }
}
