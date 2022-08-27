package Exercises_02;

import java.util.Arrays;
import java.util.Scanner;

public class TheHeiganDance_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double heiganPoints = 3000000;
        int playerPoints = 18500;
        int startRow = 7;
        int startCol = 7;
        String lastSpell = "";
        boolean activeCloud = false;
        double damage = Double.parseDouble(scanner.nextLine());

        while (playerPoints > 0 && heiganPoints > 0){
            heiganPoints -= damage;
            if (activeCloud){
                playerPoints -= 3500;
                activeCloud = false;
                if (playerPoints < 0){
                    break;
                }
            }
            if (heiganPoints < 0){
                break;
            }
            String[] tokens = scanner.nextLine().split("\\s+");
            String spell = tokens[0];
            int rowDamage = Integer.parseInt(tokens[1]);
            int colDamage = Integer.parseInt(tokens[2]);
            boolean[][]field = new boolean[15][15];
            for (int row = rowDamage - 1; row <= rowDamage + 1; row++) {
                if (row >= 0 && row < field.length){
                    for (int col = colDamage - 1; col <= colDamage + 1 ; col++) {
                        if (col >= 0 && col < field.length){
                            field [row][col] = true;
                        }
                    }
                }
            }
            if (field[startRow][startCol]){
                if (isRowValid(field, startRow - 1) && !field[startRow - 1][startCol]){
                    startRow--;
                }else if (isColValid(field, startCol + 1) && !field[startRow][startCol + 1]){
                    startCol++;
                }else if (isRowValid(field, startRow + 1) && !field[startRow + 1][startCol]){
                    startRow++;
                }else if (isColValid(field, startCol - 1) && !field[startRow][startCol - 1]){
                    startCol--;
                }
                if (field[startRow][startCol]){
                    switch (spell){
                        case "Cloud":
                            playerPoints -= 3500;
                            activeCloud = true;
                            lastSpell = "Plague Cloud";
                            break;
                        case "Eruption":
                            playerPoints -= 6000;
                            lastSpell = spell;
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid spell: " + spell);
                    }
                }
            }
        }
        if (heiganPoints > 0){
            System.out.printf("Heigan: %.2f\n", heiganPoints);
        }else {
            System.out.println("Heigan: Defeated!");
        }
        if (playerPoints > 0){
            System.out.printf("Player: %d\n", playerPoints);
        }else {
            System.out.println("Player: Killed by " + lastSpell);
        }
        System.out.println("Final position: " + startRow + ", " + startCol);
    }
    private static boolean isRowValid(boolean[][]field, int startRow){
        return startRow >= 0 && startRow < field[startRow].length;
    }
    private static boolean isColValid(boolean[][]field, int startCol){
        return startCol >= 0 && startCol < field.length;
    }
}
