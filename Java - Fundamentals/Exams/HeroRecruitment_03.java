package Exam_02;

import java.util.*;

public class HeroRecruitment_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, List<String>> heroes = new LinkedHashMap<>();
        while (!input.equals("End")) {
            String firstCommand = input.split("\\s+")[0];
            String heroName = input.split("\\s+")[1];
            switch (firstCommand) {
                case "Enroll":
                    if (!heroes.containsKey(heroName)) {
                        heroes.put(heroName, new ArrayList<>());
                    } else {
                        System.out.printf("%s is already enrolled.\n", heroName);
                    }
                    break;
                case "Learn":
                    String spellName = input.split("\\s+")[2];
                    if (heroes.containsKey(heroName)) {
                        List<String> spellBook = heroes.get(heroName);
                        if (!spellBook.contains(spellName)) {
                            spellBook.add(spellName);
                        } else {
                            System.out.printf("%s has already learnt %s\n", heroName, spellName);
                        }
                    } else {
                        System.out.printf("%s doesn't exist.\n", heroName);
                    }
                    break;
                case "Unlearn":
                    String spellForUnlearn = input.split("\\s+")[2];
                        if (heroes.containsKey(heroName)) {
                            List<String> spellBook = heroes.get(heroName);
                            if (spellBook.contains(spellForUnlearn)) {
                                spellBook.remove(spellForUnlearn);
                            } else {
                                System.out.printf("%s doesn't know %s.\n", heroName, spellForUnlearn);
                            }
                        } else {
                            System.out.printf("%s doesn't exist.\n", heroName);
                        }
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println("Heroes:");
        for (Map.Entry<String, List<String>> entry : heroes.entrySet()) {
            System.out.printf("== %s: ", entry.getKey());
            for (int index = 0; index < entry.getValue().size(); index++) {
                if (index != entry.getValue().size() - 1) {
                    System.out.printf("%s, ", entry.getValue().get(index));
                } else {
                    System.out.print(entry.getValue().get(index));
                }
            }
            System.out.println();
        }
    }
}
