package Lab_03;

import java.util.Arrays;
import java.util.Scanner;

public class CondenseArrayToNumber_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputArr = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        while (inputArr.length > 1){
            int[] newArr = new int[inputArr.length - 1];
            for (int i = 0; i < newArr.length; i++) {
                newArr[i] = inputArr[i] + inputArr[i + 1];

            }
            inputArr = newArr;
        }
        System.out.println(inputArr[0]);
    }
}
