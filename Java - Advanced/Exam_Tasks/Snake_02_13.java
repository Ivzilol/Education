package ExamPreparetion_01;

import java.util.Scanner;

public class Snake_02_13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeMatrix = Integer.parseInt(scanner.nextLine());
        String[][] territory = new String[sizeMatrix][sizeMatrix];
        int currentPositionRow = 0;
        int currentPositionCol = 0;
        int firstBorrowRow = 0;
        int firstBorrowCol = 0;
        int secondBorrowRow = 0;
        int secondBorrowCol = 0;
        int findBorrows = 0;

        for (int row = 0; row < sizeMatrix; row++) {
            String[] input = scanner.nextLine().split("");
            for (int col = 0; col < sizeMatrix; col++) {
                territory[row][col] = input[col];
                if (territory[row][col].equals("S")) {
                    currentPositionRow = row;
                    currentPositionCol = col;
                }
                if (territory[row][col].equals("B") && findBorrows == 0) {
                    findBorrows++;
                    firstBorrowRow = row;
                    firstBorrowCol = col;
                } else if (territory[row][col].equals("B") && findBorrows == 1) {
                    secondBorrowRow = row;
                    secondBorrowCol = col;
                }
            }
        }

        int eatenFood = 0;
        boolean isOut = false;
        while (true) {
            if (eatenFood >= 10) {
                break;
            }
            String command = scanner.nextLine();
            int oldPositionRow = currentPositionRow;
            int oldPositionCol = currentPositionCol;
            switch (command) {
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
                territory[oldPositionRow][oldPositionCol] = ".";
                isOut = true;
                break;
            }
            if (territory[currentPositionRow][currentPositionCol].equals("*")) {
                territory[oldPositionRow][oldPositionCol] = ".";
                territory[currentPositionRow][currentPositionCol] = "S";
                eatenFood++;
            }
            if (currentPositionRow == firstBorrowRow && currentPositionCol == firstBorrowCol) {
                territory[oldPositionRow][oldPositionCol] = ".";
                territory[firstBorrowRow][firstBorrowCol] = ".";
                currentPositionRow = secondBorrowRow;
                currentPositionCol = secondBorrowCol;
                territory[currentPositionRow][currentPositionCol] = "S";
                continue;
            }
            if (currentPositionRow == secondBorrowRow && currentPositionCol == secondBorrowCol) {
                territory[oldPositionRow][oldPositionCol] = ".";
                territory[secondBorrowRow][secondBorrowCol] = ".";
                currentPositionRow = firstBorrowRow;
                currentPositionCol = firstBorrowCol;
                territory[currentPositionRow][currentPositionCol] = "S";
                continue;
            }
            if (territory[currentPositionRow][currentPositionCol].equals("-")) {
                territory[oldPositionRow][oldPositionCol] = ".";
                territory[currentPositionRow][currentPositionCol] = "S";
            }
        }
        if (isOut) {
            System.out.println("Game over!");
        } else {
            System.out.println("You won! You fed the snake.");
        }
        System.out.println("Food eaten: " + eatenFood);

        for (int row = 0; row < sizeMatrix; row++) {
            for (int col = 0; col < sizeMatrix; col++) {
                System.out.print(territory[row][col]);
            }
            System.out.println();
        }
    }
}
