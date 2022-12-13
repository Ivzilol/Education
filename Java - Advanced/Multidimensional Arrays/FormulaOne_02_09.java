package ExamPreparetion_01;

import java.util.Scanner;

public class FormulaOne_02_09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixSize = Integer.parseInt(scanner.nextLine());
        int countCommands = Integer.parseInt(scanner.nextLine());
        String[][] trace = new String[matrixSize][matrixSize];
        int currentPositionPlayerRow = 0;
        int currentPositionPlayerCol = 0;

        for (int row = 0; row < matrixSize; row++) {
            String[] input = scanner.nextLine().split("");
            for (int col = 0; col < matrixSize; col++) {
                trace[row][col] = input[col];
                if (trace[row][col].equals("P")) {
                    currentPositionPlayerRow = row;
                    currentPositionPlayerCol = col;
                }
            }
        }
        boolean isFinished = false;
        for (int index = 0; index < countCommands; index++) {
            int oldPositionPlayerRow = currentPositionPlayerRow;
            int oldPositionPlayerCol = currentPositionPlayerCol;
            String commands = scanner.nextLine();
            switch (commands) {
                case "down":
                    currentPositionPlayerRow++;
                    break;
                case "up":
                    currentPositionPlayerRow--;
                    break;
                case "right":
                    currentPositionPlayerCol++;
                    break;
                case "left":
                    currentPositionPlayerCol--;
                    break;
            }
            if (currentPositionPlayerRow < 0 &&
                    !trace[oldPositionPlayerRow][oldPositionPlayerCol].equals("T") &&
                    !trace[oldPositionPlayerRow][oldPositionPlayerCol].equals("B")) {
                currentPositionPlayerRow = matrixSize - 1;
                trace[oldPositionPlayerRow][oldPositionPlayerCol] = ".";
                trace[currentPositionPlayerRow][currentPositionPlayerCol] = "P";
            }
            if (currentPositionPlayerRow >= matrixSize &&
                    !trace[oldPositionPlayerRow][oldPositionPlayerCol].equals("T") &&
                    !trace[oldPositionPlayerRow][oldPositionPlayerCol].equals("B")) {
                currentPositionPlayerRow = 0;
                trace[oldPositionPlayerRow][oldPositionPlayerCol] = ".";
                trace[currentPositionPlayerRow][currentPositionPlayerCol] = "P";
            }
            if (currentPositionPlayerCol < 0 &&
                    !trace[oldPositionPlayerRow][oldPositionPlayerCol].equals("T") &&
                    !trace[oldPositionPlayerRow][oldPositionPlayerCol].equals("B")) {
                currentPositionPlayerCol = matrixSize - 1;
                trace[oldPositionPlayerRow][oldPositionPlayerCol] = ".";
                trace[currentPositionPlayerRow][currentPositionPlayerCol] = "P";
            }
            if (currentPositionPlayerCol >= matrixSize &&
                    !trace[oldPositionPlayerRow][oldPositionPlayerCol].equals("T") &&
                    !trace[oldPositionPlayerRow][oldPositionPlayerCol].equals("B")) {
                currentPositionPlayerCol = 0;
                trace[oldPositionPlayerRow][oldPositionPlayerCol] = ".";
                trace[currentPositionPlayerRow][currentPositionPlayerCol] = "P";
            }
            if (trace[currentPositionPlayerRow][currentPositionPlayerCol].equals("B")) {
                trace[oldPositionPlayerRow][oldPositionPlayerCol] = ".";
                switch (commands) {
                    case "down":
                        currentPositionPlayerRow++;
                        break;
                    case "up":
                        currentPositionPlayerRow--;
                        break;
                    case "right":
                        currentPositionPlayerCol++;
                        break;
                    case "left":
                        currentPositionPlayerCol--;
                        break;
                }
            }
            if (trace[currentPositionPlayerRow][currentPositionPlayerCol].equals("T")) {
                currentPositionPlayerRow = oldPositionPlayerRow;
                currentPositionPlayerCol = oldPositionPlayerCol;
            }
            if (trace[currentPositionPlayerRow][currentPositionPlayerCol].equals("F")) {
                trace[oldPositionPlayerRow][oldPositionPlayerCol] = ".";
                trace[currentPositionPlayerRow][currentPositionPlayerCol] = "P";
                isFinished = true;
                break;
            }
            if (trace[currentPositionPlayerRow][currentPositionPlayerCol].equals(".")) {
                trace[oldPositionPlayerRow][oldPositionPlayerCol] = ".";
                trace[currentPositionPlayerRow][currentPositionPlayerCol] = "P";
            }
        }
        if (isFinished) {
            System.out.println("Well done, the player won first place!");
        } else {
            System.out.println("Oh no, the player got lost on the track!");
        }
        printMatrix(matrixSize, trace);
    }

    private static void printMatrix(int matrixSize, String[][] trace) {
        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                System.out.print(trace[row][col]);
            }
            System.out.println();
        }
    }
}
