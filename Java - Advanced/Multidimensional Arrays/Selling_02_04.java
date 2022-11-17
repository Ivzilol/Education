package ExamPreparetion_01;

import java.util.Scanner;

public class Selling_02_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int currentPositionRow = 0;
        int currentPositionCol = 0;
        int positionFirstPillarsRow = 0;
        int positionFirstPillarsCol = 0;
        int positionSecondPillarsRow = 0;
        int positionSecondPillarsCol = 0;
        int countPillars = 0;

        String[][] bakery = new String[size][size];
        for (int row = 0; row < size; row++) {
            String[] input = scanner.nextLine().split("");
            for (int col = 0; col < size; col++) {
                bakery[row][col] = input[col];
                if (bakery[row][col].equals("S")) {
                    currentPositionRow = row;
                    currentPositionCol = col;
                } else if (bakery[row][col].equals("O") && countPillars == 0) {
                    countPillars++;
                    positionFirstPillarsRow = row;
                    positionFirstPillarsCol = col;
                } else if (bakery[row][col].equals("O") && countPillars > 0) {
                    positionSecondPillarsRow = row;
                    positionSecondPillarsCol = col;
                }
            }
        }
        int money = 0;
        while (money < 50) {
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
                    || currentPositionRow >= size || currentPositionCol >= size) {
                bakery[oldPositionRow][oldPositionCol] = "-";
                break;
            } else if (currentPositionRow == positionFirstPillarsRow &&
                    currentPositionCol == positionFirstPillarsCol) {
                bakery[oldPositionRow][oldPositionCol] = "-";
                bakery[currentPositionRow][currentPositionCol] = "-";
                currentPositionRow = positionSecondPillarsRow;
                currentPositionCol = positionSecondPillarsCol;
                bakery[currentPositionRow][currentPositionCol] = "S";
            } else if (currentPositionRow == positionSecondPillarsRow &&
                    currentPositionCol == positionSecondPillarsCol) {
                bakery[oldPositionRow][oldPositionCol] = "-";
                bakery[currentPositionRow][currentPositionCol] = "-";
                currentPositionRow = positionFirstPillarsRow;
                currentPositionCol = positionFirstPillarsCol;
                bakery[currentPositionRow][currentPositionCol] = "S";
            } else if (bakery[currentPositionRow][currentPositionCol].equals("-")) {
                bakery[oldPositionRow][oldPositionCol] = "-";
                bakery[currentPositionRow][currentPositionCol] = "S";
            } else {
                money += Integer.parseInt(bakery[currentPositionRow][currentPositionCol]);
                bakery[oldPositionRow][oldPositionCol] = "-";
                bakery[currentPositionRow][currentPositionCol] = "S";

            }
        }
        if (money < 50) {
            System.out.println("Bad news, you are out of the bakery.");
        } else {
            System.out.println("Good news! You succeeded in collecting enough money!");
        }
        System.out.printf("Money: %d\n", money);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(bakery[row][col]);
            }
            System.out.println();
        }
    }
}