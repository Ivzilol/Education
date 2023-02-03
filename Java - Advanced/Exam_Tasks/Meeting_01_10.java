package ExamPreparetion_01;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Meeting_01_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputMales = scanner.nextLine();
        String inputFemales = scanner.nextLine();

        ArrayDeque<Integer> male = new ArrayDeque<>();
        ArrayDeque<Integer> females = new ArrayDeque<>();

        Arrays.stream(inputMales.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(male::push);
        Arrays.stream(inputFemales.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(females::add);

        int matches = 0;

        while (!male.isEmpty() && !females.isEmpty()) {
            int currentMale = male.peek();
            int currentFemale = females.peek();
            if (currentMale <= 0) {
                male.poll();
                continue;
            }
            if (currentFemale <= 0) {
                females.pop();
                continue;
            }
            if (currentMale % 25 == 0) {
                male.poll();
                if (extracted(male, females)) break;
                male.poll();
            }
            if (currentFemale % 25 == 0) {
                females.pop();
                if (extracted(male, females)) break;
                females.pop();
            }
            if (extracted(male, females)) break;
            if (currentMale == currentFemale) {
                male.poll();
                females.pop();
                matches++;
            } else {
                females.pop();
                currentMale -= 2;
                male.poll();
                male.push(currentMale);
            }
        }
        print(male, females, matches);
    }

    private static void print(ArrayDeque<Integer> male, ArrayDeque<Integer> females, int matches) {
        System.out.println("Matches: " + matches);
        if (male.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            System.out.print("Males left: ");
            for (int currentMale : male) {
                if (male.size() != 1) {
                    System.out.print(currentMale + ", ");
                } else {
                    System.out.print(currentMale);
                }
                male.poll();
            }
            System.out.println();
        }
        if (females.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            System.out.print("Females left: ");
            for (int currentFemale : females) {
                if (females.size() != 1) {
                    System.out.print(currentFemale + ", ");
                } else {
                    System.out.print(currentFemale);
                }
                females.pop();
            }
            System.out.println();
        }
    }

    private static boolean extracted(ArrayDeque<Integer> male, ArrayDeque<Integer> females) {
        return male.isEmpty() || females.isEmpty();
    }
}
