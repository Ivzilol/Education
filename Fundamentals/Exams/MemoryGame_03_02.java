package Exam_preparation_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MemoryGame_03_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> elements = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());

        String input = scanner.nextLine();
        int countTurns = 0;
        while (!input.equals("end") && elements.size() > 0) {
            countTurns++;
            boolean isEquals = true;
            int firstNumber = Integer.parseInt(input.split(" ")[0]);
            int secondNumber = Integer.parseInt(input.split(" ")[1]);
            String firstElement = "";
            int indexFirstElement = 0;
            String secondElement = "";
            int indexSecondElement = 0;
            for (int index = 0; index <= elements.size() - 1; index++) {
                if (firstNumber == secondNumber || firstNumber < 0 || secondNumber < 0 ||
                        firstNumber > elements.size() - 1 || secondNumber > elements.size() - 1) {
                    int middleIndex = elements.size() / 2;
                    String countAdd = String.valueOf(countTurns);
                    String firstAddElement = '-' + countAdd + 'a';
                    elements.add(middleIndex, firstAddElement);
                    elements.add(middleIndex + 1, firstAddElement);
                    System.out.println("Invalid input! Adding additional elements to the board");
                    isEquals = false;
                    break;
                }
                if (index == firstNumber) {
                    firstElement = elements.get(index);
                    indexFirstElement = index;
                }
                if (index == secondNumber) {
                    secondElement = elements.get(index);
                    indexSecondElement = index;
                }
                if (firstElement.equals(secondElement)) {
                    elements.remove(indexFirstElement);
                    if (indexSecondElement <= 0) {
                        elements.remove(0);
                    } else {
                        elements.remove(indexSecondElement - 1);
                    }
                    System.out.printf("Congrats! You have found matching elements - %s!\n", firstElement);
                    isEquals = false;
                    break;
                }
            }
            if (isEquals) {
                System.out.println("Try again!");
            }
            input = scanner.nextLine();
        }
        if (elements.size() < 1) {
            System.out.printf("You have won in %d turns!", countTurns);
        } else {
            System.out.println("Sorry you lose :(");
            for (String symbols : elements) {
                System.out.print(symbols + " ");
            }
        }
    }
}
