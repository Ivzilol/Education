package Exam_preparation_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovingTarget_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> targets = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("End")) {
            List<String> commands = Arrays.stream(input.split(" ")).collect(Collectors.toList());
            String typeCommand = commands.get(0);
            if (typeCommand.equals("Shoot")) {
                int index = Integer.parseInt(commands.get(1));
                int power = Integer.parseInt(commands.get(2));
                if (index <= targets.size() - 1 && index >= 0){
                    int currentNumber = targets.get(index);
                    currentNumber = currentNumber - power;
                    if (currentNumber <= 0){
                        targets.remove(index);
                    }else {
                        targets.set(index, currentNumber);
                    }
                }
            } else if (typeCommand.equals("Add")) {
                int index = Integer.parseInt(commands.get(1));
                int value = Integer.parseInt(commands.get(2));
                if (index <= targets.size() - 1 && index >= 0) {
                    targets.add(index, value);
                } else {
                    System.out.println("Invalid placement!");
                }
            } else if (typeCommand.equals("Strike")) {
                int index = Integer.parseInt(commands.get(1));
                int value = Integer.parseInt(commands.get(2));
                if (targets.size() - 1 >= index && targets.size() - 1 >= index + value
                        && index - value >= 0) {
                    int radius = value * 2 + 1;
                    for (int i = 0; i < radius; i++) {
                        targets.remove(index - value);
                    }
                } else {
                    System.out.println("Strike missed!");
                }
            }
            input = scanner.nextLine();
        }
        for (int i = 0; i <= targets.size() - 1; i++) {
            int currentNumber = targets.get(i);
            System.out.print(currentNumber);
            if (i == targets.size() - 1) {
                break;
            }
            System.out.print("|");
        }
    }
}
