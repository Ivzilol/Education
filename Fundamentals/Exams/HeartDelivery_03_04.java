package Exam_preparation_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HeartDelivery_03_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> houses = Arrays.stream(scanner.nextLine().split("@+")).
                map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();
        int lastPosition = 0;
        while (!input.equals("Love!")) {
            String command = input.split(" ")[0];
            switch (command){
                case "Jump":
                    int sizeJump = Integer.parseInt(input.split(" ")[1]);
                    lastPosition += sizeJump;
                    if (lastPosition >= houses.size()){
                        lastPosition = 0;
                    }
                    if (houses.get(lastPosition) == 0){
                        System.out.printf("Place %d already had Valentine's day.\n", lastPosition);
                        break;
                    }
                    houses.set(lastPosition, houses.get(lastPosition) - 2);
                    if (houses.get(lastPosition) == 0){
                        System.out.printf("Place %d has Valentine's day.\n", lastPosition);
                        break;
                    }
                }
            input = scanner.nextLine();
        }
        int countSuccessful = 0;
        int countUnsuccessful = 0;
        for (int index = 0; index <= houses.size() - 1; index++) {
            int currentNumber = houses.get(index);
            if (currentNumber == 0) {
                countSuccessful++;
            } else {
                countUnsuccessful++;
            }
        }
        System.out.printf("Cupid's last position was %d.\n", lastPosition);
        if (countUnsuccessful == 0) {
            System.out.print("Mission was successful.");
        } else {
            System.out.printf("Cupid has failed %d places.", countUnsuccessful);
        }
    }
}
