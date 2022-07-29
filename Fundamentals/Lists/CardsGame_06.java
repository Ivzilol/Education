package Exercise_05;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CardsGame_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> firstPlayer = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> secondPlayer = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());


        while (firstPlayer.size() > 0 && secondPlayer.size() > 0) {
            int firstPlayerCard = firstPlayer.remove(0);
            int secondPlayerCard = secondPlayer.remove(0);
            if (firstPlayerCard > secondPlayerCard) {
                firstPlayer.add(firstPlayerCard);
                firstPlayer.add(secondPlayerCard);

            } else if (secondPlayerCard > firstPlayerCard) {
                secondPlayer.add(secondPlayerCard);
                secondPlayer.add(firstPlayerCard);
            }
        }
        if (firstPlayer.size() > 0) {
            int sum = 0;
            for (int sumCards : firstPlayer) {
                sum += sumCards;
            }
            System.out.printf("First player wins! Sum: %d", sum);
        } else {
            int sum = 0;
            for (int sumCards : secondPlayer) {
                sum += sumCards;
            }
            System.out.printf("Second player wins! Sum: %d", sum);
        }
    }
}
