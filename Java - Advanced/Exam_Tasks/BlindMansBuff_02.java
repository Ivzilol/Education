package Exam;

import java.util.Arrays;
import java.util.Scanner;

public class BlindMansBuff_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] input = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int field_n = input[0];
        int field_m = input[1];

        int currentPositionRow = 0;
        int currentPositionCol = 0;

        String[][] field = new String[field_n][field_m];
        for (int row = 0; row < field.length; row++) {
            String[] inputMatrix = scanner.nextLine().split("\\s+");
            for (int col = 0; col < inputMatrix.length; col++) {
                field[row][col] = inputMatrix[col];
                if (field[row][col].equals("B")) {
                    currentPositionRow = row;
                    currentPositionCol = col;
                }
            }
        }
        String commands = scanner.nextLine();
        int touchOpponent = 0;
        int numberMoves = 0;
        while (!commands.equals("Finish")) {
            if (touchOpponent == 3) {
                break;
            }
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
            if (currentPositionRow < 0 || currentPositionRow >= field_n || currentPositionCol < 0
                    || currentPositionCol >= field_m) {
                currentPositionRow = oldPositionRow;
                currentPositionCol = oldPositionCol;
                commands = scanner.nextLine();
                continue;

            }

            if (field[currentPositionRow][currentPositionCol].equals("O")) {
                currentPositionRow = oldPositionRow;
                currentPositionCol = oldPositionCol;
                commands = scanner.nextLine();
                continue;
            }
            if (field[currentPositionRow][currentPositionCol].equals("P")) {
                touchOpponent++;
                field[oldPositionRow][oldPositionCol] = "-";
                field[currentPositionRow][currentPositionCol] = "B";
                numberMoves++;
            }
            if (field[currentPositionRow][currentPositionCol].equals("-")) {
                numberMoves++;
                field[oldPositionRow][oldPositionCol] = "-";
                field[currentPositionRow][currentPositionCol] = "B";
            }
            commands = scanner.nextLine();
        }

        System.out.println("Game over!");
        System.out.printf("Touched opponents: %d Moves made: %d", touchOpponent, numberMoves);
    }
}
