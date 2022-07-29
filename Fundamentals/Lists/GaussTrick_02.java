package Lab_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GaussTrick_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numberList = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        int sizeList = numberList.size();
        for (int index = 0; index < sizeList / 2; index++) {
            int firstNum = numberList.get(index);
            int secondNum = numberList.get(numberList.size() - 1);

            numberList.set(index, firstNum + secondNum);
            numberList.remove(numberList.size() - 1);
        }
        for (int number : numberList) {
            System.out.print(number + " ");
        }
        System.out.println(numberList.toString().replaceAll("[\\[\\],]", ""));
    }
}
