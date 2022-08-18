package Lab_01;

import java.util.ArrayDeque;
import java.util.Scanner;

public class HotPotato_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] children = scanner.nextLine().split(" ");
        int number = Integer.parseInt(scanner.nextLine());
        ArrayDeque<String> childrenQueue = new ArrayDeque<>();
        for (int i = 0; i < children.length; i++) {
            childrenQueue.offer(children[i]);

        }
        while (childrenQueue.size() != 1) {
            for (int i = 1; i < number; i++) {
                String currentChild = childrenQueue.poll();
                childrenQueue.offer(currentChild);
            }
            String childRemove = childrenQueue.poll();
            System.out.println("Removed " + childRemove);
        }
        String lastChild = childrenQueue.peek();
        System.out.printf("Last is %s", lastChild);
    }
}
