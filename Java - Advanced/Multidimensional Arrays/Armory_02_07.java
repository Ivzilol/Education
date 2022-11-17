package ExamPreparetion_01;

import java.util.Scanner;

public class Armory_02_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        String[][] armory = new String[size][size];
        int currentPositionRow = 0;
        int currentPositionCol = 0;
        int firstMirrorRow = 0;
        int firstMirrorCol = 0;
        int secondMirrorRow = 0;
        int secondMirrorCol = 0;
        boolean mirrorIsFind = false;
        for (int row = 0; row < size; row++) {
            String[] inputArmory = scanner.nextLine().split("");
            for (int col = 0; col < size; col++) {
                armory[row][col] = inputArmory[col];
                if (armory[row][col].equals("A")){
                    currentPositionRow = row;
                    currentPositionCol = col;
                }
                if (armory[row][col].equals("M") && !mirrorIsFind){
                    firstMirrorRow = row;
                    firstMirrorCol = col;
                    mirrorIsFind = true;
                }
                if (armory[row][col].equals("M") && mirrorIsFind){
                    secondMirrorRow = row;
                    secondMirrorCol = col;
                }
            }
        }
        int coins = 0;
        boolean isOut = false;
        while (coins < 65){
            String command = scanner.nextLine();
            int oldPositionRow = currentPositionRow;
            int oldPositionCol = currentPositionCol;
            switch (command){
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
                || currentPositionRow >= size || currentPositionCol >= size){
                armory[oldPositionRow][oldPositionCol] = "-";
                isOut = true;
                break;
            }else if (currentPositionRow == firstMirrorRow && currentPositionCol == firstMirrorCol){
                currentPositionRow = secondMirrorRow;
                currentPositionCol = secondMirrorCol;
                armory[firstMirrorRow][firstMirrorCol] = "-";
                armory[currentPositionRow][currentPositionCol] = "A";
                armory[oldPositionRow][oldPositionCol] = "-";
            } else if (currentPositionRow == secondMirrorRow && currentPositionCol == secondMirrorCol){
                currentPositionRow = firstMirrorRow;
                currentPositionCol = firstMirrorCol;
                armory[secondMirrorRow][secondMirrorCol] = "-";
                armory[currentPositionRow][currentPositionCol] = "A";
                armory[oldPositionRow][oldPositionCol] = "-";
            }else if (armory[currentPositionRow][currentPositionCol].equals("-")){
                armory[oldPositionRow][oldPositionCol] = "-";
                armory[currentPositionRow][currentPositionCol] = "A";
            }else {
                armory[oldPositionRow][oldPositionCol] = "-";
                int sumCoins = Integer.parseInt(armory[currentPositionRow][currentPositionCol]);
                coins += sumCoins;
                armory[currentPositionRow][currentPositionCol] = "A";
            }
        }
        if (isOut) {
            System.out.println("I do not need more swords!");
            System.out.printf("The king paid %d gold coins.\n", coins);
        }else {
            System.out.println("Very nice swords, I will come back for more!");
            System.out.printf("The king paid %d gold coins.\n", coins);
        }
        printMatrix(size, armory);
    }

    private static void printMatrix(int size, String[][] armory) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(armory[row][col]);
            }
            System.out.println();
        }
    }
}
