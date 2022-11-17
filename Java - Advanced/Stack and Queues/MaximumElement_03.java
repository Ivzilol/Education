package Exercises_01;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class MaximumElement_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> numbers = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            int command = Integer.parseInt(input.split("\\s+")[0]);
            switch (command) {
                case 1:
                    int number = Integer.parseInt(input.split("\\s+")[1]);
                    numbers.push(number);
                    break;
                case 2:
                    numbers.pop();
                    break;
                case 3:
                    System.out.println(Collections.max(numbers));
                    break;
            }
        }
    }
}
