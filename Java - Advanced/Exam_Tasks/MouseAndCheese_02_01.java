package ExamPreparetion_01;

import java.util.Scanner;

public class MouseAndCheese_02_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int mouseRow = -1;
        int mouseCol = -1;
        int countCheese = 0;

        char[][] matrix = new char[n][n];
        fillMatrix(scanner, n, matrix);
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                char currentSymbol = matrix[row][col];
                if (currentSymbol == 'M') {
                    mouseRow = row;
                    mouseCol = col;
                }
            }

        }
        String direction = scanner.nextLine();

        while (!direction.equals("end")) {
            matrix[mouseRow][mouseCol] = '-';
            switch (direction) {
                case "up":
                    mouseRow--;
                    break;
                case "down":
                    mouseRow++;
                    break;
                case "left":
                    mouseCol--;
                    break;
                case "right":
                    mouseCol++;
                    break;
            }
            if (mouseRow < 0 || mouseRow >= n || mouseCol < 0 || mouseCol >= n) {
                System.out.println("Where is the mouse?");
                break;
            }
            if (matrix[mouseRow][mouseCol] == 'c') {
                countCheese++;
            } else if (matrix[mouseRow][mouseCol] == 'B') {
                continue;
            }
            matrix[mouseRow][mouseCol] = 'M';
            direction = scanner.nextLine();
        }
        if (countCheese >= 5) {
            System.out.printf("Great job, the mouse is fed %d cheeses!\n", countCheese);
        } else {
            System.out.printf("The mouse couldn't eat the cheeses, she needed %d cheeses more.\n", 5 - countCheese);
        }
        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                char currentSymbol = matrix[row][col];
                System.out.print(currentSymbol);
            }
            System.out.println();
        }
    }

    private static void fillMatrix(Scanner scanner, int n, char[][] matrix) {
        for (int row = 0; row < n; row++) {
            String rowContent = scanner.nextLine();
            char[] rowSymbols = rowContent.toCharArray();
            matrix[row] = rowSymbols;
        }
    }
}
