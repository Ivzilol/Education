package Exam_preparation_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShootForTheWin_02_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> targets = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();
        int shotTargets = 0;
        while (!input.equals("End")) {
            int shot = Integer.parseInt(input);
            if (shot >= 0 && shot <= targets.size() - 1) {
                shotTargets++;
                int target = targets.get(shot);
                for (int index = 0; index <= targets.size() - 1; index++) {
                    int currentTarget = targets.get(index);
                    if (currentTarget > target && currentTarget >= 0) {
                        int damage = currentTarget - target;
                        targets.set(index, damage);
                    }
                    if (currentTarget <= target && currentTarget >= 0) {
                        int damage = currentTarget + target;
                        targets.set(index, damage);
                    }
                }
                targets.set(shot, -1);
            }
            input = scanner.nextLine();
        }
        System.out.printf("Shot targets: %d -> ", shotTargets);
        for (int numbers : targets) {
            System.out.print(numbers + " ");
        }
    }
}
