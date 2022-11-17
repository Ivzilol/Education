package Exercise_03;

import java.util.Objects;
import java.util.Scanner;

public class Train_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int[] people = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(scanner.nextLine());
            sum += people[i];
        }
        for (int number : people){
            System.out.printf("%d ", number);
        }
        System.out.println();
        System.out.println(sum);
    }
}
