package Exercises_01.jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        int value = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = value++;
            }
        }
        long sum = 0;
        String command = scanner.nextLine();
        while (!command.equals("Let the Force be with you")) {
            int[] heroCoordinates = Arrays.stream(command.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] evilCoordinates = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int evilRow = evilCoordinates[0];
            int evilCol = evilCoordinates[1];

            moveEvil(matrix, evilRow, evilCol);

            sum = moveHero(matrix, sum, heroCoordinates);

            command = scanner.nextLine();
        }
        System.out.println(sum);
    }

    private static long moveHero(int[][] matrix, long sum, int[] heroCoordinates) {
        int heroRow = heroCoordinates[0];
        int heroCol = heroCoordinates[1];
        while (heroRow >= 0 && heroCol < matrix[1].length){
            if (heroRow < matrix.length && heroCol >= 0 && heroCol < matrix[0].length){
                sum += matrix[heroRow][heroCol];
            }
            heroCol++;
            heroRow--;
        }
        return sum;
    }

    private static void moveEvil(int[][] matrix, int row, int col) {
        while (row >= 0 && col >= 0) {
            if (row < matrix.length && col < matrix[0].length) {
                matrix[row][col] = 0;
            }
            row--;
            col--;
        }
    }

}
