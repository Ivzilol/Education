package Traning_03;

import java.util.*;
import java.util.stream.Collectors;

public class Messaging_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> numbersArr = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());
        List<String> stringArr = Arrays.stream(scanner.nextLine().split(""))
                .collect(Collectors.toList());
        List<String> elementsList = new ArrayList<>();
        StringBuilder output = new StringBuilder();

        for (int index = 0; index <= numbersArr.size() - 1; index++) {
            String currentElement = numbersArr.get(index);
            elementsList = List.of(currentElement.split(""));
            int sumElements = 0;
            for (int index2 = 0; index2 <= elementsList.size() - 1; index2++) {
                int currentNumber = Integer.parseInt(elementsList.get(index2));
                sumElements += currentNumber;
            }
            if (sumElements <= stringArr.size()) {
                for (int indexText = 0; indexText <= stringArr.size() - 1; indexText++) {
                    if (sumElements == indexText) {
                        String symbol = stringArr.get(indexText);
                        output.append(symbol);
                        stringArr.remove(indexText);
                    }
                }
            } else {
                output.append(stringArr.get(1));
                stringArr.remove(1);
            }
        }
        System.out.println(output);
    }
}
