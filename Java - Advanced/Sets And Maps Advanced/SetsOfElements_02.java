package Exercises_03;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class SetsOfElements_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int sizeFirstSet = Integer.parseInt(input.split("\\s+")[0]);
        int sizeSecondSetSet = Integer.parseInt(input.split("\\s+")[1]);
        Set<Integer> firstSet = new LinkedHashSet<>();
        Set<Integer> secondSet = new LinkedHashSet<>();
        for (int i = 0; i < sizeFirstSet; i++) {
            int input1 = Integer.parseInt(scanner.nextLine());
            firstSet.add(input1);
        }
        for (int i = 0; i < sizeSecondSetSet; i++) {
            int input2 = Integer.parseInt(scanner.nextLine());
            secondSet.add(input2);
        }
        //Set<Integer> duplicateElements = new LinkedHashSet<>();
        //for (int number : firstSet) {
        //    if (secondSet.contains(number)) {
        //        duplicateElements.add(number);
        //    }
        //}
        //for (int number : duplicateElements) {
        //    System.out.print(number + " ");
        //}
        firstSet.retainAll(secondSet);
        firstSet.forEach(n -> System.out.print(n + " "));
    }
}
