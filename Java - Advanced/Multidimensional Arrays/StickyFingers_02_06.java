package ExamPreparetion_01;

import java.util.Scanner;

public class StickyFingers_02_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(",");
        String[][] field = new String[size][size];
        int positionDillingerRow = 0;
        int positionDillingerCol = 0;

        for (int row = 0; row < size; row++) {
            String[] input = scanner.nextLine().split("\\s+");
            for (int col = 0; col < size; col++) {
                field[row][col] = input[col];
                if (field[row][col].equals("D")) {
                    positionDillingerRow = row;
                    positionDillingerCol = col;
                }
            }
        }
        int stoleMoney = 0;
        for (int index = 0; index < commands.length; index++) {
            String command = commands[index];
            int oldPositionRow = positionDillingerRow;
            int oldPositionCol = positionDillingerCol;
            switch (command) {
                case "right":
                    positionDillingerCol++;
                    break;
                case "left":
                    positionDillingerCol--;
                    break;
                case "down":
                    positionDillingerRow++;
                    break;
                case "up":
                    positionDillingerRow--;
                    break;
            }
            if (positionDillingerRow < 0 || positionDillingerCol < 0
                    || positionDillingerRow >= size || positionDillingerCol >= size) {
                positionDillingerRow = oldPositionRow;
                positionDillingerCol = oldPositionCol;
                System.out.println("You cannot leave the town, there is police outside!");
                continue;
            } else if (field[positionDillingerRow][positionDillingerCol].equals("$")) {
                int moneyBored = positionDillingerRow * positionDillingerCol;
                stoleMoney += moneyBored;
                field[oldPositionRow][oldPositionCol] = "+";
                field[positionDillingerRow][positionDillingerCol] = "D";
                System.out.printf("You successfully stole %d$.\n", moneyBored);
            } else if (field[positionDillingerRow][positionDillingerCol].equals("+")) {
                field[oldPositionRow][oldPositionCol] = "+";
                field[positionDillingerRow][positionDillingerCol] = "D";
            } else {
                field[oldPositionRow][oldPositionCol] = "+";
                field[positionDillingerRow][positionDillingerCol] = "#";
                System.out.printf("You got caught with %d$, and you are going to jail.\n", stoleMoney);
                pintField(field);
                return;
            }
        }
        System.out.printf("Your last theft has finished successfully with %d$ in your pocket.\n", stoleMoney);
        pintField(field);
    }

    private static void pintField(String[][] field) {
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field.length; col++) {
                if (col != field.length - 1) {
                    System.out.print(field[row][col] + " ");
                } else {
                    System.out.print(field[row][col]);
                }
            }
            System.out.println();
        }
    }
}
