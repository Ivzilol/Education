
package ExamPreparetion_01;

import java.util.Scanner;

public class Bee_02_12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeMatrix = Integer.parseInt(scanner.nextLine());
        String[][] field = new String[sizeMatrix][sizeMatrix];
        int currentPositionRow = 0;
        int currentPositionCol = 0;

        for (int row = 0; row < sizeMatrix; row++) {
            String[] inputField = scanner.nextLine().split("");
            for (int col = 0; col < sizeMatrix; col++) {
                field[row][col] = inputField[col];
                if (field[row][col].equals("B")) {
                    currentPositionRow = row;
                    currentPositionCol = col;
                }
            }
        }
        int pollinatedFlowers = 0;
        boolean isOut = false;
        String commands = String.valueOf(scanner.nextLine());
        while (!commands.equals("End")) {
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

            if (currentPositionRow < 0 || currentPositionCol < 0
                    || currentPositionRow >= sizeMatrix || currentPositionCol >= sizeMatrix) {
                isOut = true;
                field[oldPositionRow][oldPositionCol] = ".";
                break;
            }
            if (field[currentPositionRow][currentPositionCol].equals("O")) {
                field[oldPositionRow][oldPositionCol] = ".";
                field[currentPositionRow][currentPositionCol] = "B";
                oldPositionRow = currentPositionRow;
                oldPositionCol = currentPositionCol;
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
                field[oldPositionRow][oldPositionCol] = ".";
            }
            if (field[currentPositionRow][currentPositionCol].equals("f")) {
                field[oldPositionRow][oldPositionCol] = ".";
                field[currentPositionRow][currentPositionCol] = "B";
                pollinatedFlowers++;
            }
            if (field[currentPositionRow][currentPositionCol].equals(".")) {
                field[oldPositionRow][oldPositionCol] = ".";
                field[currentPositionRow][currentPositionCol] = "B";
            }
            commands = scanner.nextLine();
        }
        if (isOut) {
            System.out.println("The bee got lost!");
        }
        if (pollinatedFlowers >= 5) {
            System.out.printf("Great job, the bee manage to pollinate %d flowers!\n", pollinatedFlowers);
        } else {
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more\n", 5 - pollinatedFlowers);
        }
        for (int row = 0; row < sizeMatrix; row++) {
            for (int col = 0; col < sizeMatrix; col++) {
                System.out.print(field[row][col]);
            }
            System.out.println();
        }
    }

    private void move(int currentPosRow, int currenPosCol, String commands) {
        
    }
}
