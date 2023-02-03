package ExamPreparetion_01;

import java.util.Scanner;

public class CookingJourney_02_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[][] pastryShop = new String[size][size];
        int currentRow = 0;
        int currentCol = 0;
        int firstPillarRow = 0;
        int firstPillarCol = 0;
        int secondPillarRow = 0;
        int secondPillarCol = 0;
        int countPilar = 0;

        for (int row = 0; row < size; row++) {
            String[] input = scanner.nextLine().split("");
            for (int col = 0; col < size; col++) {
                pastryShop[row][col] = input[col];
                if (pastryShop[row][col].equals("S")) {
                    currentRow = row;
                    currentCol = col;
                }
                if (pastryShop[row][col].equals("P") && countPilar > 0) {
                    secondPillarRow = row;
                    secondPillarCol = col;
                }
                if (pastryShop[row][col].equals("P") && countPilar == 0) {
                    firstPillarRow = row;
                    firstPillarCol = col;
                    countPilar++;
                }
            }
        }
        int money = 0;

        while (money < 50) {
            String command = scanner.nextLine();
            int oldRow = currentRow;
            int oldCol = currentCol;
            if (command.equals("left")) {
                currentCol--;
            } else if (command.equals("right")) {
                currentCol++;
            } else if (command.equals("up")) {
                currentRow--;
            } else if (command.equals("down")) {
                currentRow++;
            }

            if (currentRow < 0 || currentCol < 0 || currentRow >= size
                    || currentCol >= size) {
                pastryShop[oldRow][oldCol] = "-";
                break;
            }
            if (currentRow == firstPillarRow && currentCol == firstPillarCol) {
                pastryShop[oldRow][oldCol] = "-";
                currentRow = secondPillarRow;
                currentCol = secondPillarCol;
                pastryShop[firstPillarRow][firstPillarCol] = "-";
                pastryShop[secondPillarRow][secondPillarCol] = "S";
            } else if (currentRow == secondPillarRow && currentCol == secondPillarCol) {
                pastryShop[oldRow][oldCol] = "-";
                currentRow = firstPillarRow;
                currentCol = firstPillarCol;
                pastryShop[secondPillarRow][secondPillarCol] = "-";
                pastryShop[firstPillarRow][firstPillarCol] = "S";
            } else if (pastryShop[currentRow][currentCol].equals("-")) {
                pastryShop[oldRow][oldCol] = "-";
                pastryShop[currentRow][currentCol] = "S";
            } else {
                money += Integer.parseInt(pastryShop[currentRow][currentCol]);
                pastryShop[oldRow][oldCol] = "-";
                pastryShop[currentRow][currentCol] = "S";
            }
        }
        if (money < 50) {
            System.out.println("Bad news! You are out of the pastry shop.");
        } else {
            System.out.println("Good news! You succeeded in collecting enough money!");
        }
        System.out.printf("Money: %d\n", money);
        for (int row = 0; row < pastryShop.length; row++) {
            for (int col = 0; col < pastryShop.length; col++) {
                System.out.print(pastryShop[row][col]);
            }
            System.out.println();
        }
    }
}
