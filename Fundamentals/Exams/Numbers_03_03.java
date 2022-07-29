package Exam_preparation_01;

import java.util.*;
import java.util.stream.Collectors;

public class Numbers_03_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbersList = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());

        double sumAllNumbers = 0;
        boolean isTrue = false;
        for (int index = 0; index <= numbersList.size() - 1; index++) {
            int currentNumber = numbersList.get(index);
            sumAllNumbers += currentNumber;
        }
        double averageNumber = sumAllNumbers / numbersList.size();
        List<Integer> greatNumbers = new ArrayList<>();
        for (int index = 0; index <= numbersList.size() - 1; index++) {
            int currentNumber = numbersList.get(index);
            if (currentNumber > averageNumber) {
                greatNumbers.add(currentNumber);
            }
        }
        List<Integer> finalGreatNumbers = new ArrayList<>();
        if (greatNumbers.size() > 5) {
            isTrue = true;
            for (int index = 0; index <= greatNumbers.size() - 1; index++) {
                int currentNumber = greatNumbers.get(index);
                int greatCount = greatNumbers.size() - 5;
                int count = 0;
                for (int index2 = 0; index2 <= greatNumbers.size() - 1; index2++) {
                    int number = greatNumbers.get(index2);
                    if (index == index2) {
                        continue;
                    }
                    if (currentNumber > number) {
                        count++;
                    }
                }
                if (count >= greatCount) {
                    finalGreatNumbers.add(currentNumber);
                }
            }
        }
        if (isTrue) {
            finalGreatNumbers.sort(Comparator.reverseOrder());
            for (int numbers : finalGreatNumbers) {
                System.out.print(numbers + " ");
            }
            return;
        }
        if (!greatNumbers.isEmpty()) {
            greatNumbers.sort(Comparator.reverseOrder());
            for (int numbers : greatNumbers) {
                System.out.print(numbers + " ");
            }
        } else {
            System.out.print("No");
        }
    }
}
