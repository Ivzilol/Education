package Lab_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListManipulationAdvanced_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numberList = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();
        while (!input.equals("end")) {
            List<String> commands = Arrays.stream(input.split(" ")).collect(Collectors.toList());
            String command = commands.get(0);
            switch (command) {
                case "Contains":
                    int containsNumber = Integer.parseInt(commands.get(1));
                    boolean isContains = false;
                    for (int index = 0; index < numberList.size(); index++) {
                        int currentNumber = numberList.get(index);
                        if (containsNumber == currentNumber) {
                            System.out.println("Yes");
                            isContains = true;
                        }
                    }
                    if (!isContains) {
                        System.out.println("No such number");
                    }
                    break;
                case "Print":
                    String commandEvenOrOdd = commands.get(1);
                    if (commandEvenOrOdd.equals("even")) {
                        for (int index = 0; index < numberList.size(); index++) {
                            int currentNumber = numberList.get(index);
                            if (currentNumber % 2 == 0) {
                                System.out.print(currentNumber + " ");
                            }
                        }
                        System.out.println();
                    } else if (commandEvenOrOdd.equals("odd")) {
                        for (int index = 0; index < numberList.size(); index++) {
                            int currentNumber = numberList.get(index);
                            if (currentNumber % 2 != 0) {
                                System.out.print(currentNumber + " ");
                            }
                        }
                        System.out.println();
                    }
                    break;
                case "Get":
                    String sum = commands.get(1);
                    int sumNumbers = 0;
                    for (int index = 0; index < numberList.size(); index++) {
                        int currentNumber = numberList.get(index);
                        sumNumbers += currentNumber;
                    }
                    System.out.println(sumNumbers);
                    break;
                case "Filter":
                    String condition = commands.get(1);
                    int number = Integer.parseInt(commands.get(2));
                    if (condition.equals("<")) {
                        for (int index = 0; index < numberList.size(); index++) {
                            int currentNumber = numberList.get(index);
                            if (currentNumber < number) {
                                System.out.print(currentNumber + " ");
                            }
                        }
                    } else if (condition.equals(">")) {
                        for (int index = 0; index < numberList.size(); index++) {
                            int currentNumber = numberList.get(index);
                            if (currentNumber > number) {
                                System.out.print(currentNumber + " ");
                            }
                        }
                    } else if (condition.equals(">=")) {
                        for (int index = 0; index < numberList.size(); index++) {
                            int currentNumber = numberList.get(index);
                            if (currentNumber >= number) {
                                System.out.print(currentNumber + " ");
                            }
                        }
                    } else if (condition.equals("<=")) {
                        for (int index = 0; index < numberList.size(); index++) {
                            int currentNumber = numberList.get(index);
                            if (currentNumber <= number) {
                                System.out.print(currentNumber + " ");
                            }
                        }
                    }
                    System.out.println();
                    break;
            }
            input = scanner.nextLine();
        }
    }
}
