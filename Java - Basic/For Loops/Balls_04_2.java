package Traning04;

import java.util.Scanner;

public class Balls_04_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberBalls = Integer.parseInt(scanner.nextLine());

        int redBalls = 0;
        int orangeBalls = 0;
        int yellowBalls = 0;
        int whiteBalls = 0;
        int blackBalls = 0;
        int totalPoints = 0;


        for (int i = 1; i <= numberBalls; i++) {
            String colorBall = scanner.nextLine();
            if (colorBall.equals("red")){
                totalPoints += 5;
                redBalls++;
            }else if (colorBall.equals("orange")){
                totalPoints += 10;
                orangeBalls++;
            }else if (colorBall.equals("yellow")){
                totalPoints += 15;
                yellowBalls++;
            }else if (colorBall.equals("white")){
                totalPoints += 20;
                whiteBalls++;
            }else if (colorBall.equals("black")){
                totalPoints /= 2;
                blackBalls++;
            }

        }
        int allBalls = redBalls + orangeBalls + yellowBalls + whiteBalls + blackBalls;
        int otherBalls = numberBalls - allBalls;
        System.out.printf("Total points: %d%n",totalPoints);
        System.out.printf("Red balls: %d%n",redBalls);
        System.out.printf("Orange balls: %d%n",orangeBalls);
        System.out.printf("Yellow balls: %d%n",yellowBalls);
        System.out.printf("White balls: %d%n",whiteBalls);
        System.out.printf("Other colors picked: %d%n",otherBalls);
        System.out.printf("Divides from black balls: %d",blackBalls);

    }
}
