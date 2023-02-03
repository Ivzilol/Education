package ExamPreparetion_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Python_02_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixSize = Integer.parseInt(scanner.nextLine());
        List<String> commands = Arrays.stream(scanner.nextLine()
                        .split(",\\s+"))
                .collect(Collectors.toList());
        String[][] screen = new String[matrixSize][matrixSize];
        int currentPositionRow = 0;
        int currentPositionCol = 0;
        int numberFood = 1;
        for (int row = 0; row < screen.length; row++) {
            String[] input = scanner.nextLine().split("\\s+");
            for (int col = 0; col < screen.length; col++) {
                screen[row][col] = input[col];
                if (screen[row][col].equals("s")) {
                    currentPositionRow = row;
                    currentPositionCol = col;
                }
                if (screen[row][col].equals("f")) {
                    numberFood++;
                }
            }
        }
        int lengthPython = 1;
        for (String currentCommand : commands) {
            int oldPositionRow = currentPositionRow;
            int oldPositionCol = currentPositionCol;
            switch (currentCommand) {
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
            if (currentPositionRow < 0) {
                currentPositionRow = matrixSize - 1;
                screen[oldPositionRow][oldPositionCol] = "*";
            }
            if (currentPositionRow >= matrixSize) {
                currentPositionRow = 0;
                screen[oldPositionRow][oldPositionCol] = "*";
            }
            if (currentPositionCol < 0) {
                currentPositionCol = matrixSize - 1;
                screen[oldPositionRow][oldPositionCol] = "*";
            }
            if (currentPositionCol >= matrixSize) {
                currentPositionCol = 0;
                screen[oldPositionRow][oldPositionCol] = "*";
            }
            if (screen[currentPositionRow][currentPositionCol].equals("f")) {
                lengthPython++;
                screen[currentPositionRow][currentPositionCol] = "s";
                screen[oldPositionRow][oldPositionCol] = "*";
            }
            if (screen[currentPositionRow][currentPositionCol].equals("*")) {
                screen[currentPositionRow][currentPositionCol] = "s";
                screen[oldPositionRow][oldPositionCol] = "*";
            }
            if (screen[currentPositionRow][currentPositionCol].equals("e")) {
                System.out.println("You lose! Killed by an enemy!");
                return;
            }
        }
        if (lengthPython == numberFood) {
            System.out.printf("You win! Final python length is %d", lengthPython);
        } else {
            System.out.printf("You lose! There is still %d food to be eaten.", numberFood - lengthPython);
        }
    }
}
