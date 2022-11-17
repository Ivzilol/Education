package Traning_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TakeSkipRope_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Character> numbers = new ArrayList<>();
        List<Character> symbols = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        for (int index = 0; index <= input.length() - 1; index++) {
            if (input.charAt(index) >= 48 && input.charAt(index) <= 57) {
                numbers.add(input.charAt(index));
            } else {
                symbols.add(input.charAt(index));
            }
        }
        List<Integer> takeList = new ArrayList<>();
        List<Integer> skipList = new ArrayList<>();
        for (int index = 0; index <= numbers.size() - 1; index++) {
            int currentNumber = Integer.parseInt(String.valueOf(numbers.get(index)));
            if (index % 2 == 0) {
                takeList.add(currentNumber);
            } else {
                skipList.add(currentNumber);
            }
        }
        for (int indexTakeList = 0; indexTakeList <= takeList.size() - 1; indexTakeList++) {
            int takeNumber = takeList.get(indexTakeList);
            for (int indexTextList = 0; indexTextList <= symbols.size() - 1; indexTextList++) {
                if (takeNumber > indexTextList) {
                    output.append(symbols.get(0));
                    symbols.remove(0);
                } else {
                    break;
                }
            }
            for (int indexSkipList = 0; indexSkipList < skipList.get(0); indexSkipList++) {
                if (skipList.size() <= 1) {
                    break;
                }
                symbols.remove(0);
            }
            skipList.remove(0);
        }
        System.out.println(output);
    }
}
