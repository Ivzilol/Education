package Lab_05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindEvensOrOdds_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputNumbers = scanner.nextLine();
        String evenOrOdd = scanner.nextLine();
        int firstNumber = Integer.parseInt(inputNumbers.split(" ")[0]);
        int lastNumber = Integer.parseInt(inputNumbers.split(" ")[1]);
        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
        for (int index = firstNumber; index <= lastNumber; index++) {
            if (index % 2 == 0) {
                evenNumbers.add(index);
            } else {
                oddNumbers.add(index);
            }
        }
        if (evenOrOdd.equals("odd")) {
            oddNumbers.forEach(a -> System.out.printf("%d ", a));
        } else {
            evenNumbers.forEach(a -> System.out.printf("%d ", a));
        }
    }
}
