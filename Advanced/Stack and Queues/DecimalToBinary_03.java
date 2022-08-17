package Lab_01;

import java.util.ArrayDeque;
import java.util.Scanner;

public class DecimalToBinary_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int decimal = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        if (decimal == 0){
            System.out.println(0);
        }
        while (decimal != 0) {
            int leftOVer = decimal % 2;
            stack.push(leftOVer);
            decimal = decimal / 2;
        }
        for (Integer integer : stack)
            System.out.print(integer);
    }
}
