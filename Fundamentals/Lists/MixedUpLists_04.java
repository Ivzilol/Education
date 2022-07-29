package Traning_03;

import java.util.*;
import java.util.stream.Collectors;

public class MixedUpLists_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> firstList = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> secondList = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());

        List<Integer> mixList = new ArrayList<>();
        List<Integer> rangeList = new ArrayList<>();
        boolean isEmpty1 = false;
        boolean isEmpty2 = false;
        for (int index1 = 0; index1 <= firstList.size() - 1; index1++) {
            if (secondList.isEmpty()) {
                isEmpty2 = true;
            }
            if (isEmpty2) {
                int rangeNumber = firstList.get(index1);
                rangeList.add(rangeNumber);
                continue;
            }
            int numberFirstList = firstList.get(index1);
            mixList.add(numberFirstList);
            firstList.remove(index1);
            for (int index2 = secondList.size() - 1; index2 >= 0; index2--) {
                if (isEmpty1) {
                    int rangeNumber = secondList.get(index2);
                    rangeList.add(rangeNumber);
                    continue;
                }
                index1 = -1;
                int numberSecondList = secondList.get(index2);
                mixList.add(numberSecondList);
                secondList.remove(index2);
                if (firstList.isEmpty()) {
                    isEmpty1 = true;
                    continue;
                }
                break;
            }
        }
        rangeList.sort(Comparator.naturalOrder());
        int minNumber = rangeList.get(0);
        int maxNumber = rangeList.get(1);
        List<Integer> finalList = new ArrayList<>();
        for (int index = 0; index <= mixList.size() - 1; index++) {
            int currentNumber = mixList.get(index);
            if (currentNumber > minNumber && currentNumber < maxNumber) {
                finalList.add(currentNumber);
            }
        }
        finalList.sort(Comparator.naturalOrder());
        for (int number : finalList) {
            System.out.print(number + " ");
        }
    }
}
