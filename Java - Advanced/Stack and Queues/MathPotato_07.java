package Lab_01;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class MathPotato_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] children = scanner.nextLine().split(" ");
        int number = Integer.parseInt(scanner.nextLine());
        ArrayDeque<String> childrenQueue = new ArrayDeque<>();
        Collections.addAll(childrenQueue, children);
        int cycle = 1;
        while (childrenQueue.size() > 1){
            for (int i = 1; i < number; i++) {
                childrenQueue.offer(childrenQueue.poll());
                if (cycle == 2 || cycle == 3 || cycle == 5 || cycle == 7 || cycle == 11 || cycle == 13 || cycle == 17 ||
                cycle == 19 || cycle == 23){
                    System.out.println("Prime " + childrenQueue.peek());
                }else {
                    System.out.println("Removed " + childrenQueue.poll());
                }
                cycle++;
            }
        }
        System.out.println("Last is " + childrenQueue.poll());

    }
}
