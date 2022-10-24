package Exam_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Numbers_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> listNumber = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("Finish")) {
            switch (input.split("\\s+")[0]) {
                case "Add":
                    int numberForAdd = Integer.parseInt(input.split("\\s+")[1]);
                    listNumber.add(numberForAdd);
                    break;
                case "Remove":
                    int numberForRemove = Integer.parseInt(input.split("\\s+")[1]);
                    for (int index = 0; index < listNumber.size(); index++) {
                        int currentNumber = listNumber.get(index);
                        if (currentNumber == numberForRemove) {
                            listNumber.remove(index);
                            break;
                        }
                    }
                    break;
                case "Replace":
                    int numberForReplace = Integer.parseInt(input.split("\\s+")[1]);
                    int newNumber = Integer.parseInt(input.split("\\s+")[2]);
                    for (int index = 0; index < listNumber.size(); index++) {
                        int currentNumber = listNumber.get(index);
                        if (currentNumber == numberForReplace) {
                            listNumber.set(index, newNumber);
                            break;
                        }
                    }
                    break;
                case "Collapse":
                    int collapseNumber = Integer.parseInt(input.split("\\s+")[1]);
                    for (int index = 0; index < listNumber.size(); index++) {
                        int currentNumber = listNumber.get(index);
                        if (currentNumber < collapseNumber) {
                            listNumber.remove(index);
                            index -= 1;
                        }
                    }
                    break;
            }
            input = scanner.nextLine();
        }

        for (Integer integer : listNumber) {
            System.out.print(integer + " ");
        }
    }
}

