package Lab_05;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RemoveNegativesAndReverse_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numberList = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        numberList.removeIf(e -> e < 0);
        if (numberList.isEmpty()) {
            System.out.println("empty");
            return;
        }
        Collections.reverse(numberList);
        System.out.println(numberList.toString().replaceAll("[\\[\\],]", ""));
    }
}
