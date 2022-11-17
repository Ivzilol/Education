package Exercises_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Crossfire_07 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] matrix;

    public static void main(String[] args) throws IOException {
        int[] dimensions = getElements(reader.readLine());
        int rows = dimensions[0];
        int cols = dimensions[1];
        matrix = fillMatrix(rows, cols);
        String input = reader.readLine();
        while (!input.equals("Nuke it from orbit")) {
            int[] numbers = getElements(input);
            int row = numbers[0];
            int col = numbers[1];
            int radius = numbers[2];
            int start = Math.max(0, row - radius);
            int end = Math.min(matrix.length - 1, row + radius);
            for (int i = start; i <= end; i++) {
                if (isValid(i, col) && i != row) {
                    removeElement(i, col);
                }
            }
            end = Math.max(0, col - radius);
            for (int i = col + radius; i >= end; i--) {
                if (isValid(row, i)) {
                    removeElement(row, i);
                }
            }
            input = reader.readLine();
        }
        printMatrix();
    }

    private static void removeElement(int row, int col) {
        int rowSize = matrix[row].length - 1;
        if (rowSize > 0) {
            int elementToRemove = matrix[row][col];
            int index = 0;
            int[] arr = new int[rowSize];
            for (int i = 0; i < matrix[row].length; i++) {
                int currentNum = matrix[row][i];
                if (currentNum != elementToRemove) {
                    arr[index++] = currentNum;
                }
            }
            matrix[row] = arr;
        } else {
            int[][] newMatrix = new int[matrix.length - 1][];
            boolean finished = false;
            int rowMatrix = 0;
            int newMatrixRow = 0;

            int elementToRemove = matrix[row][col];
            while (!finished) {
                int matrixRowSize = matrix[rowMatrix].length;
                if (matrixRowSize > 1 || matrix[rowMatrix][col] != elementToRemove) {
                    newMatrix[newMatrixRow] = matrix[rowMatrix];
                    newMatrixRow++;
                }
                rowMatrix++;
                if (rowMatrix >= matrix.length) {
                    finished = true;
                }
            }
            matrix = newMatrix;
        }
    }

    private static boolean isValid(int row, int col) {
        return (row >= 0) && (row < matrix.length) &&
                (col >= 0) && (col < matrix[row].length);
    }

    private static int[][] fillMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        int counter = 1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = counter++;
            }
        }
        return matrix;
    }

    private static int[] getElements(String input) {
        return Arrays
                .stream(input.split("[\\s]+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void printMatrix() {
        StringBuilder output = new StringBuilder();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int currentNum = matrix[row][col];

                output.append(currentNum).append(" ");
            }
            output.append(System.lineSeparator());
        }
        System.out.print(output);
    }
}
