package Traning_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DrumSet_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double savingsGabsy = Double.parseDouble(scanner.nextLine());
        List<Integer> drumSet = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();
        List<Integer> workSet = new ArrayList<>(drumSet);

        while (!input.equals("Hit it again, Gabsy!")) {
            int hitPower = Integer.parseInt(input.split(" ")[0]);
            for (int index = 0; index <= workSet.size() - 1; index++) {
                workSet.set(index, workSet.get(index) - hitPower);
                int numberDrumSet = drumSet.get(index);
                if (workSet.get(index) <= 0) {
                    if (numberDrumSet * 3 >= savingsGabsy) {
                        if (index == workSet.size() - 1) {
                            drumSet.remove(index);
                            workSet.remove(index);
                            continue;
                        } else {
                            drumSet.remove(index);
                            workSet.remove(index);
                            index -= 1;
                            continue;
                        }
                    }
                    workSet.set(index, drumSet.get(index));
                    savingsGabsy -= numberDrumSet * 3;
                }
            }
            input = scanner.nextLine();
        }
        for (int number : workSet) {
            System.out.print(number + " ");
        }
        System.out.println();
        System.out.printf("Gabsy has %.2flv.", savingsGabsy);
    }
}
