package ExamPreparetion_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TreasureHunt_02_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputMap = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int rowField = inputMap[0];
        int colField = inputMap[1];

        int currentPositionRow = 0;
        int currentPositionCol = 0;

        int treasureRow = 0;
        int treasureCol = 0;

        String[][] treasureField = new String[rowField][colField];
        for (int row = 0; row < rowField; row++) {
            String[] input = scanner.nextLine().split("\\s+");
            for (int col = 0; col < colField; col++) {
                treasureField[row][col] = input[col];
                if (treasureField[row][col].equals("Y")) {
                    currentPositionRow = row;
                    currentPositionCol = col;
                } else if (treasureField[row][col].equals("X")) {
                    treasureRow = row;
                    treasureCol = col;
                }
            }
        }
        List<String> wayToTreasure = new ArrayList<>();
        String commands = scanner.nextLine();
        while (!commands.equals("Finish")) {
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
                    || currentPositionRow >= rowField || currentPositionCol >= colField) {
                currentPositionRow = oldPositionRow;
                currentPositionCol = oldPositionCol;
            } else if (treasureField[currentPositionRow][currentPositionCol].equals("T")) {
                currentPositionRow = oldPositionRow;
                currentPositionCol = oldPositionCol;
            } else {
                treasureField[oldPositionRow][oldPositionCol] = "-";
                treasureField[currentPositionRow][currentPositionCol] = "Y";
                wayToTreasure.add(commands);
            }
            commands = scanner.nextLine();
        }
        if (currentPositionRow == treasureRow && currentPositionCol == treasureCol) {
            System.out.println("I've found the treasure!");
            System.out.print("The right path is ");
            for (int index = 0; index < wayToTreasure.size(); index++) {
                String currentWord = wayToTreasure.get(index);
                if (index != wayToTreasure.size() - 1) {
                    System.out.print(currentWord + ", ");
                } else {
                    System.out.print(currentWord);
                }
            }
        } else {
            System.out.print("The map is fake!");
        }
    }
}
