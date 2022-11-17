package Lab_02;

import java.util.Arrays;
import java.util.Scanner;

public class PrintDiagonalsOfSquareMatrix_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[n][n];

        for (int row = 0; row < n; row++) {
            int[] rowData = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[row] = rowData;
        }
        for (int row = 0; row < n; row++) {
            System.out.print(matrix[row][row] + " ");
        }
        System.out.println();
        for (int row = n; row >= 0; row--) {
            System.out.print(matrix[row - 1][matrix.length - row] + " ");
            if (row <= 1){
                break;
            }
        }
    }
}
