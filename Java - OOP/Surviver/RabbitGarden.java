package Survivor;

import java.util.Scanner;

public class RabbitGarden {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        simple input
//        5
//        rc---
//        cc---
//        -----
//        ---cc
//        ---cc


        int sizeGarden = Integer.parseInt(scanner.nextLine());
        String[][] garden = new String[sizeGarden][sizeGarden];

        for (int row = 0; row < sizeGarden; row++) {
            String[] input = scanner.nextLine().split("");
            System.arraycopy(input, 0, garden[row], 0, sizeGarden);
        }
        int currentPositionRow = 0;
        int currentPositionCol = 0;
        int numberJumps = 1;
        while (true) {
            int oldPositionRow = currentPositionRow;
            int oldPositionCol = currentPositionCol;
            if (garden[currentPositionRow + 1][currentPositionCol].equals("c")) {
                garden[currentPositionRow + 1][currentPositionCol] = "-";

            }
            if (garden[currentPositionRow][currentPositionCol + 1].equals("c")) {
                garden[currentPositionRow][currentPositionCol + 1] = "-";
            }
            if (garden[currentPositionRow + 1][currentPositionCol + 1].equals("c")) {
                garden[currentPositionRow + 1][currentPositionCol + 1] = "-";
            }

            if (checkNumberCarrots(sizeGarden, garden)) break;

            boolean nextCarrotIsFind = false;
            for (int row = 0; row < sizeGarden; row++) {
                if (nextCarrotIsFind) {
                    break;
                }
                for (int col = 0; col < sizeGarden; col++) {
                    if (garden[row][col].equals("c")) {
                        currentPositionRow = row;
                        currentPositionCol = col;
                        numberJumps++;
                        garden[oldPositionRow][oldPositionCol] = "-";
                        garden[currentPositionRow][currentPositionCol] = "r";
                        nextCarrotIsFind = true;
                        break;
                    }
                }
            }
        }
        System.out.println(numberJumps);
    }

    private static boolean checkNumberCarrots(int sizeGarden, String[][] garden) {
        int countCarrot = 0;
        for (int row = 0; row < sizeGarden; row++) {
            for (int col = 0; col < sizeGarden; col++) {
                if (garden[row][col].equals("c")) {
                    countCarrot++;
                }
            }
        }
        return countCarrot == 0;
    }
}

