package Exercise_03;

import java.util.Scanner;

public class ArrayRotation_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] array = input.split(" ");
        int countR = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= countR; i++) {
            String firstElement = array[0];
            for (int j = 0; j < array.length - 1; j++) {
                array[j] = array[j + 1];
            }
            array[array.length - 1] = firstElement;
        }
        for (String item : array) {
            System.out.printf("%s ", item);
        }
    }
}
