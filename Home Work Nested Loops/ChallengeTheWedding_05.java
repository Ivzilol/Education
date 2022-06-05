package Traning06;

import java.util.Scanner;

public class ChallengeTheWedding_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int man = Integer.parseInt(scanner.nextLine());
        int women = Integer.parseInt(scanner.nextLine());
        int tables = Integer.parseInt(scanner.nextLine());
        int countTables = 0;
        for (int i = 1; i <= man ; i++) {
            for (int j = 1; j <= women ; j++) {
                System.out.printf("(%d <-> %d) ", i, j);
                countTables++;
                if (countTables >= tables){
                    return;
                }
            }
        }
    }
}
