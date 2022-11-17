package Exercises_01;

import java.util.ArrayDeque;
import java.util.Scanner;

public class RecursiveFibonacci_06 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayDeque<Long> fibonacci = new ArrayDeque<>();
        long n = Integer.parseInt(input.nextLine());
        System.out.println(getFibonacci(fibonacci, n));
    }

    private static Long getFibonacci(ArrayDeque<Long> fibonacci, Long N) {
        if(N ==1 || N < 2){
            return 1L;
        }else {
            fibonacci.offer(0L);
            fibonacci.offer(1L);
            for (int i = 0; i < N; i++) {
                long Sum = fibonacci.poll()+fibonacci.peek();
                fibonacci.offer(Sum);
            }
        }
        fibonacci.poll();
        return fibonacci.peek();
    }
}
