package ExamPreparetion_01;

import java.util.*;

public class FlowerWreaths_01_13 {

    private static final int SUM_FOR_WREATH = 15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputLilies = scanner.nextLine();
        String inputRoses = scanner.nextLine();

        ArrayDeque<Integer> lilies = new ArrayDeque<>();
        ArrayDeque<Integer> roses = new ArrayDeque<>();

        Arrays.stream(inputLilies.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(lilies::push);
        Arrays.stream(inputRoses.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(roses::offer);
        List<Integer> collectionFlowers = new ArrayList<>();
        int numberWreath = 0;
        while (!lilies.isEmpty() && !roses.isEmpty()) {
            int currentLilies = lilies.peek();
            int currentRoses = roses.peek();
            int sumFlowers = currentLilies + currentRoses;
            if (sumFlowers == SUM_FOR_WREATH) {
                numberWreath++;
                lilies.poll();
                roses.pop();
            } else if (sumFlowers > SUM_FOR_WREATH) {
                currentLilies -= 2;
                lilies.poll();
                lilies.offer(currentLilies);
            } else {
                collectionFlowers.add(currentLilies);
                collectionFlowers.add(currentRoses);
                lilies.poll();
                roses.pop();
            }
        }
        int sumStoredFlowers = collectionFlowers
                .stream()
                .mapToInt(Integer::intValue)
                .sum() / SUM_FOR_WREATH;
        int allWreath = sumStoredFlowers + numberWreath;
        if (allWreath >= 5) {
            System.out.printf("You made it, you are going to the competition with %d wreaths!", allWreath);
        } else {
            System.out.printf("You didn't make it, you need %d wreaths more!", 5 - allWreath);
        }
    }
}
