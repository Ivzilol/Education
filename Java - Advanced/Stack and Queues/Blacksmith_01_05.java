package ExamPreparetion_01;

import java.util.*;

public class Blacksmith_01_05 {
    private static final int GLADIUS = 70;
    private static final int SHAMSHIR = 80;
    private static final int KATANA = 90;
    private static final int SABRE = 110;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputSteel = scanner.nextLine();
        String inputCarbon = scanner.nextLine();
        ArrayDeque<Integer> steel = new ArrayDeque<>();
        ArrayDeque<Integer> carbon = new ArrayDeque<>();
        Map<String, Integer> swords = new TreeMap<>();
        swords.put("Gladius", 0);
        swords.put("Shamshir", 0);
        swords.put("Katana", 0);
        swords.put("Sabre", 0);
        Arrays.stream(inputSteel.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(steel::offer);
        Arrays.stream(inputCarbon.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(carbon::push);
        int countSwords = 0;
        while (!steel.isEmpty() && !carbon.isEmpty()) {
            int currentSteel = steel.peek();
            int currentCarbon = carbon.peek();
            int sum = currentSteel + currentCarbon;
            switch (sum) {
                case GLADIUS:
                    int cuurentGladius = swords.get("Gladius");
                    swords.put("Gladius", cuurentGladius + 1);
                    steel.pop();
                    carbon.poll();
                    countSwords++;
                    break;
                case SHAMSHIR:
                    int currentShamshir = swords.get("Shamshir");
                    swords.put("Shamshir", currentShamshir + 1);
                    steel.pop();
                    carbon.poll();
                    countSwords++;
                    break;
                case KATANA:
                    int currentKatana = swords.get("Katana");
                    swords.put("Katana", currentKatana + 1);
                    steel.pop();
                    carbon.poll();
                    countSwords++;
                    break;
                case SABRE:
                    int currentSabre = swords.get("Sabre");
                    swords.put("Sabre", currentSabre + 1);
                    steel.pop();
                    carbon.poll();
                    countSwords++;
                    break;
                default:
                    steel.pop();
                    carbon.poll();
                    int newCarbon = currentCarbon + 5;
                    carbon.push(newCarbon);
                    break;
            }
        }
        if (countSwords > 0) {
            System.out.printf("You have forged %d swords.\n", countSwords);
        } else {
            System.out.println("You did not have enough resources to forge a sword.");
        }
        if (!steel.isEmpty()) {
            System.out.print("Steel left: ");
            for (int index = 0; index < steel.size(); index++) {
                int number = steel.peek();
                if (steel.size() != 1) {
                    System.out.print(number + ", ");
                    steel.pop();
                }
            }
            System.out.println(steel.pop());
        } else {
            System.out.println("Steel left: none");
        }
        if (!carbon.isEmpty()) {
            System.out.print("Carbon left: ");
            for (int index = 0; index < carbon.size(); index++) {
                int number = carbon.peek();
                if (carbon.size() != 1) {
                    System.out.print(number + ", ");
                    carbon.pop();
                }
            }
            System.out.println(carbon.pop());
        } else {
            System.out.println("Carbon left: none");
        }
        for (Map.Entry<String, Integer> entry : swords.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
            }
        }
    }
}
