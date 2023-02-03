package ExamPreparetion_01;

import java.util.Scanner;

public class PawnWars_02_08 {

    private static final int SIZE_MATRIX = 8;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] matrix = new char[8][8];

        int wRow = -1;
        int wCol = -1;
        int bRow = -1;
        int bCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            char[] arr = scanner.nextLine().toCharArray();
            for (int col = 0; col < arr.length; col++) {
                matrix[row][col] = arr[col];
                if (arr[col] == 'w') {
                    wRow = row;
                    wCol = col;
                } else if (arr[col] == 'b') {
                    bRow = row;
                    bCol = col;
                }
            }
        }

        boolean isHit = false;

        while (wRow != 0 && bRow != 7 && !isHit) {
            if (whitePawHitBlack(wRow, wCol, bRow, bCol)) {

                String coordinates = findCoordinates(bRow, bCol);
                System.out.printf("Game over! White capture on %s.", coordinates);
                isHit = true;

            }
            wRow -= 1;

            if (blackPawHitWhite(bRow, bCol, wRow, wCol)) {

                String coordinates = findCoordinates(wRow, wCol);
                System.out.printf("Game over! Black capture on %s.", coordinates);
                isHit = true;

            }
            bRow += 1;

        }

        if (!isHit) {
            System.out.print(wRow == 0
                    ? "Game over! White pawn is promoted to a queen at " + findCoordinates(wRow, wCol) + "."
                    : "Game over! Black pawn is promoted to a queen at " + findCoordinates(bRow, bCol) + "."
            );
        }
    }

    private static String findCoordinates(int bRow, int bCol) {
        char[] col = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char[] row = new char[]{'8', '7', '6', '5', '4', '3', '2', '1'};
        return String.valueOf(col[bCol]) + row[bRow];
    }

    private static boolean blackPawHitWhite(int bRow, int bCol, int wRow, int wCol) {
        return bRow + 1 == wRow && (bCol + 1 == wCol || bCol - 1 == wCol);
    }

    private static boolean whitePawHitBlack(int wRow, int wCol, int bRow, int bCol) {
        return wRow - 1 == bRow && (wCol + 1 == bCol || wCol - 1 == bCol);
    }
}
