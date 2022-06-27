package Exercises_04;

import java.util.Scanner;

public class TennisRanklist_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberTournaments = Integer.parseInt(scanner.nextLine());
        int pointsStart = Integer.parseInt(scanner.nextLine());
        double winNumbers = 0;
        int points = 0;
        for (int i = 1; i <= numberTournaments; i++) {
            String round = scanner.nextLine();

            if (round.equals("W")){
                points += 2000;
                winNumbers ++;
            }else if (round.equals("F")){
                points += 1200;
            }else if (round.equals("SF")){
                points += 720;
            }
        }
        double percentWin = (winNumbers / numberTournaments * 100);
        int totalPoint = pointsStart + points;
        System.out.printf("Final points: %d%n", totalPoint);
        System.out.printf("Average points: %d%n", points / numberTournaments);
        System.out.printf("%.2f%%", percentWin);
    }
}
