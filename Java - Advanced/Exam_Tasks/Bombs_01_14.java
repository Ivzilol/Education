package ExamPreparetion_01;

import java.util.*;

public class Bombs_01_14 {

    private static final int DATURA_BOMBS = 40;
    private static final int CHERRY_BOMBS = 60;
    private static final int SMOKE_DECOY_BOMBS = 120;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputBombEffects = scanner.nextLine();
        String inputBombCasing = scanner.nextLine();

        ArrayDeque<Integer> bombEffects = new ArrayDeque<>();
        ArrayDeque<Integer> bombCasing = new ArrayDeque<>();

        Arrays.stream(inputBombEffects.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(bombEffects::offer);
        Arrays.stream(inputBombCasing.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(bombCasing::push);

        Map<String, Integer> bombPouch = new TreeMap<>();
        bombPouch.put("Datura Bombs:", 0);
        bombPouch.put("Cherry Bombs:", 0);
        bombPouch.put("Smoke Decoy Bombs:", 0);

        while (!bombEffects.isEmpty() && !bombCasing.isEmpty()) {
            if (bombPouch.get("Datura Bombs:") >= 3 && bombPouch.get("Cherry Bombs:") >= 3
                && bombPouch.get("Smoke Decoy Bombs:") >= 3) {
                break;
            }
            int currentBombEffects = bombEffects.peek();
            int currentBombCasing = bombCasing.peek();
            int sum = currentBombEffects + currentBombCasing;
            if (sum == DATURA_BOMBS) {
                bombPouch.put("Datura Bombs:", bombPouch.get("Datura Bombs:") + 1);
                bombEffects.poll();
                bombCasing.pop();
            } else if (sum == CHERRY_BOMBS) {
                bombPouch.put("Cherry Bombs:", bombPouch.get("Cherry Bombs:") + 1);
                bombEffects.poll();
                bombCasing.pop();
            } else if (sum == SMOKE_DECOY_BOMBS) {
                bombPouch.put("Smoke Decoy Bombs:", bombPouch.get("Smoke Decoy Bombs:") + 1);
                bombEffects.poll();
                bombCasing.pop();
            } else {
                currentBombCasing -= 5;
                bombCasing.pop();
                bombCasing.push(currentBombCasing);
            }
        }

        if (bombPouch.get("Datura Bombs:") >= 3 && bombPouch.get("Cherry Bombs:") >= 3
                && bombPouch.get("Smoke Decoy Bombs:") >= 3) {
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        } else {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }
        printEffects(bombEffects);
        printCasings(bombCasing);
        bombPouch.forEach((key, value) -> System.out.printf("%s %d\n", key, value));
    }


    private static void printCasings(ArrayDeque<Integer> bombCasing) {
        if (bombCasing.isEmpty()) {
            System.out.println("Bomb Casings: empty");
        } else {
            System.out.print("Bomb Casings: ");
            for (Integer currentCasing : bombCasing) {
                if (bombCasing.size() != 1) {
                    System.out.print(currentCasing + ", ");
                    bombCasing.pop();
                } else {
                    System.out.print(currentCasing);
                }
            }
            System.out.println();
        }
    }

    private static void printEffects(ArrayDeque<Integer> bombEffects) {
        if (bombEffects.isEmpty()) {
            System.out.println("Bomb Effects: empty");
        } else {
            System.out.print("Bomb Effects: ");
            for (Integer currentEffect : bombEffects){
                if (bombEffects.size() != 1) {
                    System.out.print(currentEffect + ", ");
                    bombEffects.poll();
                } else {
                    System.out.print(currentEffect);
                }
            }
            System.out.println();
        }
    }
}
