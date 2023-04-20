package Survivor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ColorBalls {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        simple input
//        Red.10
//        Blue.5
//        White.5
//        White.5
//        End


        Map<String, Integer> ballMap = new LinkedHashMap<>();
        String input = scanner.nextLine();
        while (!input.equals("End")) {
            String colorBall = input.split("\\.")[0];
            int numberBall = Integer.parseInt(input.split("\\.")[1]);
            if (!ballMap.containsKey(colorBall)) {
                ballMap.put(colorBall, numberBall);
            } else {
                int currenNumberBal = ballMap.get(colorBall);
                ballMap.put(colorBall, currenNumberBal + numberBall);
            }
            input = scanner.nextLine();
        }
        if (ballMap.size() <= 1) {
            System.out.println(0);
        } else {
            int allBallInBox = ballMap.values().stream().mapToInt(v -> v).sum();
            System.out.println(allBallInBox - 1);
        }
    }
}
