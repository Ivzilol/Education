package ExamPreparetion_01;

import java.util.Scanner;

public class    RallyRacing_02_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeMatrix = Integer.parseInt(scanner.nextLine());
        String carNumber = scanner.nextLine();
        String[][] trace = new String[sizeMatrix][sizeMatrix];
        int countTunnel = 0;
        int currentPositionCarRow = 0;
        int currentPositionCarCol = 0;
        int firstPositionTunnelRow = 0;
        int firstPositionTunnelCol = 0;
        int secondPositionTunnelRow = 0;
        int secondPositionTunnelCol = 0;


        for (int row = 0; row < sizeMatrix; row++) {
            String[] input = scanner.nextLine().split("\\s+");
            for (int col = 0; col < sizeMatrix; col++) {
                trace[row][col] = input[col];
                if (trace[row][col].equals("T") && countTunnel == 0) {
                    firstPositionTunnelRow = row;
                    firstPositionTunnelCol = col;
                    countTunnel++;
                }
                if (trace[row][col].equals("T") && countTunnel > 0) {
                    secondPositionTunnelRow = row;
                    secondPositionTunnelCol = col;
                }
                if (currentPositionCarRow == row && currentPositionCarCol == col ) {
                    trace[row][col] = "C";
                }
            }
        }
        String commands = scanner.nextLine();
        boolean isFinished = false;
        int distanceCovered = 0;
        while (!commands.equals("End")) {
            int oldPositionCarRow = currentPositionCarRow;
            int oldPositionCarCol = currentPositionCarCol;
            switch (commands) {
                case "up":
                    currentPositionCarRow--;
                    break;
                case "down":
                    currentPositionCarRow++;
                    break;
                case "right":
                    currentPositionCarCol++;
                    break;
                case "left":
                    currentPositionCarCol--;
                    break;
            }
            if (trace[currentPositionCarRow][currentPositionCarCol].equals(".")) {
                trace[oldPositionCarRow][oldPositionCarCol] = ".";
                trace[currentPositionCarRow][currentPositionCarCol] = "C";
                distanceCovered += 10;
            } else if (currentPositionCarRow == firstPositionTunnelRow &&
                    currentPositionCarCol == firstPositionTunnelCol) {
                currentPositionCarRow = secondPositionTunnelRow;
                currentPositionCarCol = secondPositionTunnelCol;
                trace[oldPositionCarRow][oldPositionCarCol] = ".";
                trace[firstPositionTunnelRow][firstPositionTunnelCol] = ".";
                trace[currentPositionCarRow][currentPositionCarCol] = "C";
                distanceCovered += 30;
            } else if (currentPositionCarRow == secondPositionTunnelRow &&
                    currentPositionCarCol == secondPositionTunnelCol) {
                currentPositionCarRow = firstPositionTunnelRow;
                currentPositionCarCol = firstPositionTunnelCol;
                trace[oldPositionCarRow][oldPositionCarCol] = ".";
                trace[secondPositionTunnelRow][secondPositionTunnelCol] = ".";
                trace[currentPositionCarRow][currentPositionCarCol] = "C";
                distanceCovered += 30;
            } else if (trace[currentPositionCarRow][currentPositionCarCol].equals("F")) {
                trace[oldPositionCarRow][oldPositionCarCol] = ".";
                distanceCovered += 10;
                trace[currentPositionCarRow][currentPositionCarCol] = "C";
                isFinished = true;
            }
            if (isFinished) {
                break;
            }
            commands = scanner.nextLine();
        }
        if (isFinished) {
            System.out.printf("Racing car %s finished the stage!\n", carNumber);
        } else {
            System.out.printf("Racing car %s DNF.\n", carNumber);
        }
        System.out.printf("Distance covered %d km.\n", distanceCovered);
        for (int row = 0; row < sizeMatrix; row++) {
            for (int col = 0; col < sizeMatrix; col++) {
                System.out.print(trace[row][col]);
            }
            System.out.println();
        }
    }
}
