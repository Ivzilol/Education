package ExamPreparetion_01;

import java.util.Scanner;

public class NavyBattle_02_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixSize = Integer.parseInt(scanner.nextLine());
        String[][] battlefield = new String[matrixSize][matrixSize];
        int currentPositionRow = 0;
        int currentPositionCol = 0;


        for (int row = 0; row < matrixSize; row++) {
            String[] input = scanner.nextLine().split("");
            for (int col = 0; col < matrixSize; col++) {
                battlefield[row][col] = input[col];
                if (battlefield[row][col].equals("S")) {
                    currentPositionRow = row;
                    currentPositionCol = col;
                }
            }
        }

        int countBattleShips = 0;
        int countMines = 0;

        while (true) {
            if (countBattleShips == 3) {
                break;
            }
            if (countMines == 3) {
                break;
            }
            String commands = scanner.nextLine();
            int oldPositionRow = currentPositionRow;
            int oldPositionCol = currentPositionCol;
            switch (commands) {
                case "right":
                    currentPositionCol++;
                    break;
                case "left":
                    currentPositionCol--;
                    break;
                case "down":
                    currentPositionRow++;
                    break;
                case "up":
                    currentPositionRow--;
                    break;
            }
            if (battlefield[currentPositionRow][currentPositionCol].equals("C")) {
                battlefield[oldPositionRow][oldPositionCol] = "-";
                battlefield[currentPositionRow][currentPositionCol] = "S";
                countBattleShips++;
            }
            if (battlefield[currentPositionRow][currentPositionCol].equals("*")) {
                battlefield[oldPositionRow][oldPositionCol] = "-";
                battlefield[currentPositionRow][currentPositionCol] = "S";
                countMines++;
            }
            if (battlefield[currentPositionRow][currentPositionCol].equals("-")) {
                battlefield[oldPositionRow][oldPositionCol] = "-";
                battlefield[currentPositionRow][currentPositionCol] = "S";
            }
        }
        if (countBattleShips == 3) {
            System.out.println("Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!");
        }
        if (countMines == 3) {
            System.out.printf("Mission failed, U-9 disappeared! Last known coordinates [%d, %d]!\n",
                    currentPositionRow, currentPositionCol);
        }
        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                System.out.print(battlefield[row][col]);
            }
            System.out.println();
        }
    }
}
