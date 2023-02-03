package ExamPreparetion_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bomb_02_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixSize = Integer.parseInt(scanner.nextLine());
        List<String> commands = Arrays.stream(scanner.nextLine().split(","))
                .collect(Collectors.toList());
        String[][] field = new String[matrixSize][matrixSize];
        int currentPositionRow = 0;
        int currentPositionCol = 0;
        int countBombs = 0;
        for (int row = 0; row < matrixSize; row++) {
            String[] inputField = scanner.nextLine().split("\\s+");
            for (int col = 0; col < matrixSize; col++) {
                field[row][col] = inputField[col];
                if (field[row][col].equals("s")) {
                    currentPositionRow = row;
                    currentPositionCol = col;
                }
                if (field[row][col].equals("B")) {
                    countBombs++;
                }
            }
        }
        int neutralizedBomb = 0;
        int numberCommands = 0;
        boolean isEnd = false;
        for (int index = 0; index < commands.size(); index++) {
            numberCommands++;
            int oldPositionRow = currentPositionRow;
            int oldPositionCol = currentPositionCol;
            String currentCommand = commands.get(index);
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
            if (currentPositionRow < 0 || currentPositionCol < 0
                    || currentPositionRow >= matrixSize || currentPositionCol >= matrixSize) {
                currentPositionRow = oldPositionRow;
                currentPositionCol = oldPositionCol;
            }
            if (field[currentPositionRow][currentPositionCol].equals("e")) {
                field[currentPositionRow][currentPositionCol] = "s";
                field[oldPositionRow][oldPositionCol] = "+";
                if (neutralizedBomb == countBombs) {
                    System.out.println("Congratulations! You found all bombs!");
                } else {
                    System.out.printf("END! %d bombs left on the field", countBombs - neutralizedBomb);

                }
                return;
            }
            if (field[currentPositionRow][currentPositionCol].equals("B")) {
                field[currentPositionRow][currentPositionCol] = "s";
                field[oldPositionRow][oldPositionCol] = "+";
                neutralizedBomb++;
                System.out.println("You found a bomb!");
            }
            if (field[currentPositionRow][currentPositionCol].equals("+")) {
                field[currentPositionRow][currentPositionCol] = "s";
                field[oldPositionRow][oldPositionCol] = "+";
            }
        }
        if (neutralizedBomb == countBombs) {
            System.out.println("Congratulations! You found all bombs!");
        } else {
            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)",
                    countBombs - neutralizedBomb, currentPositionRow, currentPositionCol);
        }
    }
}
