package Exercise_03;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayModifier_09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numArr = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        String command = scanner.nextLine();

        while (!command.equals("end")) {
            String[] commandParts = command.split(" ");
            String commandType = commandParts[0];
            if (commandType.equals("swap")) {
                int index1 = Integer.parseInt(commandParts[1]);
                int element1 = numArr[index1];
                int index2 = Integer.parseInt(commandParts[2]);
                int element2 = numArr[index2];
                numArr[index1] = element2;
                numArr[index2] = element1;
            } else if (commandType.equals("multiply")) {
                int multiplyIndex1 = Integer.parseInt(commandParts[1]);
                int multiplyElement1 = numArr[multiplyIndex1];
                int multiplyIndex2 = Integer.parseInt(commandParts[2]);
                int multiplyElement2 = numArr[multiplyIndex2];
                int product = multiplyElement1 * multiplyElement2;
                numArr[multiplyIndex1] = product;
            } else if (commandType.equals("decrease")) {
                for (int i = 0; i <= numArr.length - 1; i++) {
                    numArr[i] -= 1;
                }
            }
            command = scanner.nextLine();
        }
        for (int index = 0; index <= numArr.length - 1; index++) {
            if (index != numArr.length - 1) {
                System.out.print(numArr[index] + ", ");
            } else {
                System.out.println(numArr[index]);
            }
        }
    }
}
