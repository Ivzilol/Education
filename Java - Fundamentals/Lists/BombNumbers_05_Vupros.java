package Exercise_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BombNumbers_05_Vupros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbersList = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());

        List<Integer> listOfPower = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());

        int specialNumber = listOfPower.get(0);
        int power = listOfPower.get(1);
        int radius = power * 2 + 1;
        for (int indexList = 0; indexList <= numbersList.size() - 1; indexList++) {
            int currentNumber = numbersList.get(indexList);
            if (specialNumber == currentNumber) {
                for (int index = 1; index <= radius; index++) {
                    if (indexList - power > numbersList.size() - 1) {
                        break;
                    }
                    if (numbersList.isEmpty()) {
                        break;
                    }
                    if (indexList - power < 0) {
                        numbersList.remove(0);
                        continue;
                    }
                    numbersList.remove(indexList - power);
                }
            }
        }
        int sum = 0;
        for (int number : numbersList) {
            sum += number;
        }
        System.out.println(sum);
    }
}
