package ExamPreparetion_01;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class OS_Planing_01_09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputTasks = scanner.nextLine();
        String inputThreads = scanner.nextLine();
        int taskKill = Integer.parseInt(scanner.nextLine());

        ArrayDeque<Integer> tasks = new ArrayDeque<>();
        ArrayDeque<Integer> threads = new ArrayDeque<>();

        Arrays.stream(inputTasks.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(tasks::push);
        Arrays.stream(inputThreads.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(threads::offer);

        while (true) {
            int currentTask = tasks.peek();
            int currentThread = threads.peek();
            if (currentTask == taskKill) {
                break;
            }
            if (currentThread >= currentTask) {
                tasks.poll();
                threads.pop();
            } else {
                threads.pop();
            }
        }
        System.out.printf("Thread with value %d killed task %d\n", threads.peek(), tasks.peek());
        for (Integer number : threads) {
            System.out.print(number + " ");
        }
    }
}
