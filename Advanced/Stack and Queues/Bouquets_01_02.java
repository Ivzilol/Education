package ExamPreparetion_01;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Bouquets_01_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String tulipsInput = scanner.nextLine();
        String daffodilsInput = scanner.nextLine();

        //стек
        ArrayDeque<Integer> tulips = new ArrayDeque<>();
        //опашка
        ArrayDeque<Integer> daffodils = new ArrayDeque<>();
        Arrays.stream(tulipsInput.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(tulips::push);
        Arrays.stream(daffodilsInput.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(daffodils::offer);
        int bouquets = 0;
        int leftFlowers = 0;
        while (!tulips.isEmpty() && !daffodils.isEmpty()) {
            int tulip = tulips.peek();
            int daffodil = daffodils.peek();
            int sum = tulip + daffodil;
            if (sum == 15) {
                bouquets++;
                tulips.pop(); //премахва последния елемент добавен в стека
                daffodils.poll(); // премахва първия елемент добавен в опашката
            } else if (sum > 15) {
                tulips.pop();
                tulips.push(tulip - 2); //добавя в стека
            } else {
                tulips.pop();
                daffodils.pop();
                leftFlowers += sum;
            }
        }
        while (leftFlowers > 15) {
            leftFlowers -= 15;
            bouquets++;
        }
        if (bouquets >= 5) {
            System.out.printf("You made it! You go to the competition with %d bouquets!", bouquets);
        } else {
            System.out.printf("You failed... You need more %d bouquets.", 5 - bouquets);
        }
    }
}
