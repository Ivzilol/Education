package Exercises_01;

import java.util.ArrayDeque;
import java.util.Scanner;

public class ReverseNumbersWithAStack_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] inputNumber = input.split("\\s+");

        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String number : inputNumber){
            stack.push(number);
        }
        for (String number : stack){
            System.out.print(number + " ");
        }
    }
}
