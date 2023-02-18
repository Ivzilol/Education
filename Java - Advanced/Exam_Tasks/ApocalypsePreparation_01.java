package Exam;

import java.util.*;
import java.util.stream.Collectors;

public class ApocalypsePreparation_01 {

    private static final int PATCH = 30;
    private static final int BANDAGE = 40;
    private static final int MEDKIT = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputTextiles = scanner.nextLine();
        String inputMedicament = scanner.nextLine();

        ArrayDeque<Integer> textile = new ArrayDeque<>();
        ArrayDeque<Integer> medicament = new ArrayDeque<>();

        Arrays.stream(inputTextiles.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(textile::offer);
        Arrays.stream(inputMedicament.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(medicament::push);
        Map<String, Integer> items = new TreeMap<>();
        items.put("Patch", 0);
        items.put("Bandage", 0);
        items.put("MedKit", 0);
        while (!textile.isEmpty() && !medicament.isEmpty()) {
            int currentTextile = textile.peek();
            int currentMedicament = medicament.peek();
            int sum = currentTextile + currentMedicament;

            switch (sum) {
                case PATCH:
                    items.put("Patch", items.get("Patch") + 1);
                    textile.poll();
                    medicament.pop();
                    break;
                case BANDAGE:
                    items.put("Bandage", items.get("Bandage") + 1);
                    textile.poll();
                    medicament.pop();
                    break;
                case MEDKIT:
                    items.put("MedKit", items.get("MedKit") + 1);
                    textile.poll();
                    medicament.pop();
                    break;
                default:
                    textile.poll();
                    if (sum < 100) {
                        medicament.push(medicament.pop() + 10);
                    } else {
                        medicament.pop();
                        medicament.push(medicament.pop() + sum - 100);
                        items.put("MedKit", items.get("MedKit") + 1);
                    }
            }
        }

        if (textile.isEmpty() && medicament.isEmpty()) {
            System.out.println("Textiles and medicaments are both empty.");
        } else if (textile.isEmpty()) {
            System.out.println("Textiles are empty.");
        } else {
            System.out.println("Medicaments are empty.");
        }

        items.entrySet()
                .stream()
                .sorted((left, right) -> right.getValue().compareTo(left.getValue())
                ).forEach(entry -> {
                    if (entry.getValue() > 0) {
                        System.out.printf("%s - %d\n", entry.getKey(), entry.getValue());
                    }
                });

        if (!textile.isEmpty()) {
            System.out.print("Textiles left: ");
            System.out.println(textile.stream()
                    .map(String::valueOf).collect(Collectors.joining(", ")));
        }
        if (!medicament.isEmpty()) {
            System.out.print("Medicaments left: ");
            System.out.println(medicament.stream()
                    .map(String::valueOf).collect(Collectors.joining(", ")));

        }
    }
}
