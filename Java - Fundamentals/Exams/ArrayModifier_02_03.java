package Exam_preparation_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrayModifier_02_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbersArray = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String commands = input.split(" ")[0];
            switch (commands) {
                case "swap":
                    int firstCommandSwap = Integer.parseInt(input.split(" ")[1]);
                    int secondCommandSwap = Integer.parseInt(input.split(" ")[2]);
                    int firstNumberSwap = numbersArray.get(firstCommandSwap);
                    int secondNumberSwap = numbersArray.get(secondCommandSwap);
                    numbersArray.set(secondCommandSwap, firstNumberSwap);
                    numbersArray.set(firstCommandSwap, secondNumberSwap);
                    break;
                case "multiply":
                    int firstCommandMultiply = Integer.parseInt(input.split(" ")[1]);
                    int secondCommandMultiply = Integer.parseInt(input.split(" ")[2]);
                    int firstNumberMultiply = numbersArray.get(firstCommandMultiply);
                    int secondNumberMultiply = numbersArray.get(secondCommandMultiply);
                    int result = firstNumberMultiply * secondNumberMultiply;
                    numbersArray.set(firstCommandMultiply, result);
                    break;
                case "decrease":
                    for (int index = 0; index <= numbersArray.size() - 1; index++) {
                        int currentNumber = numbersArray.get(index);
                        currentNumber -= 1;
                        numbersArray.set(index, currentNumber);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        for (int index = 0; index <= numbersArray.size() - 1; index++) {
            int numbers = numbersArray.get(index);
            if (index != numbersArray.size() - 1) {
                System.out.print(numbers + ", ");
            } else {
                System.out.print(numbers);
            }
        }
    }
}
