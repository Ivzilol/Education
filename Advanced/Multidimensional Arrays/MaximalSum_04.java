package Exercises_02;

import java.util.Arrays;
import java.util.Scanner;

public class MaximalSum_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String colsAndRows = scanner.nextLine();
        int rows = Integer.parseInt(colsAndRows.split("\\s+")[0]);
        int cols = Integer.parseInt(colsAndRows.split("\\s+")[1]);
        int[][] matrix = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            int[] rowsData = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            matrix[row] = rowsData;
        }
        int maxSum = 0;
        int rowsMaxSquare = 0;
        int colsMaxSquare = 0;
        for (int row = 0; row < matrix.length - 2; row++) {
            for (int col = 0; col < matrix[0].length - 2; col++) {
                int sum = 0;
                sum += matrix[row][col] + matrix[row][col + 1] + matrix[row][col + 2];
                sum += matrix[row + 1][col] + matrix[row + 1][col + 1] + matrix[row + 1][col + 2];
                sum += matrix[row + 2][col] + matrix[row + 2][col + 1] + matrix[row + 2][col + 2];
                if (sum > maxSum) {
                    maxSum = sum;
                    rowsMaxSquare = row;
                    colsMaxSquare = col;
                }
            }
        }
        System.out.println("Sum = " + maxSum);
        for (int i = rowsMaxSquare; i < rowsMaxSquare + 3; i++) {
            for (int j = colsMaxSquare; j < colsMaxSquare + 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
