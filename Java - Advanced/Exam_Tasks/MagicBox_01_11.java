package ExamPreparetion_01;

import java.util.*;
import java.util.stream.Collectors;

public class MagicBox_01_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputFirstMagicBox = scanner.nextLine();
        String inputSecondMagicBox = scanner.nextLine();

        ArrayDeque<Integer> firstMagicBox = new ArrayDeque<>();
        ArrayDeque<Integer> secondMagicBox = new ArrayDeque<>();

        Arrays.stream(inputFirstMagicBox.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(firstMagicBox::offer);
        Arrays.stream(inputSecondMagicBox.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(secondMagicBox::push);
        List<Integer> claimedItems = new ArrayList<>();

        while (!firstMagicBox.isEmpty() && !secondMagicBox.isEmpty()) {
            int currentItemFirstBox = firstMagicBox.peek();
            int currentItemSecondBox = secondMagicBox.peek();
            int sumItems = currentItemFirstBox + currentItemSecondBox;
            if (sumItems % 2 == 0) {
                claimedItems.add(sumItems);
                firstMagicBox.pop();
                secondMagicBox.poll();
            } else {
                secondMagicBox.poll();
                firstMagicBox.offer(currentItemSecondBox);
            }
        }
        if (firstMagicBox.isEmpty()) {
            System.out.println("First magic box is empty.");
        } else {
            System.out.println("Second magic box is empty.");
        }
        int sum = claimedItems.stream().mapToInt(Integer::intValue).sum();
        if (sum >= 90) {
            System.out.printf("Wow, your prey was epic! Value: %d", sum);
        } else {
            System.out.printf("Poor prey... Value: %d", sum);
        }
    }
}
