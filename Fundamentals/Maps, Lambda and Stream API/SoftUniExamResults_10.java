package Exercise_07;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniExamResults_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, Integer> mapUsers = new LinkedHashMap<>();
        Map<String, Integer> mapLanguage = new LinkedHashMap<>();
        while (!input.equals("exam finished")) {
            String name = input.split("-")[0];
            String language = input.split("-")[1];
            if (language.equals("banned")) {
                mapUsers.remove(name);
                input = scanner.nextLine();
                continue;
            }
            int points = Integer.parseInt(input.split("-")[2]);
            if (!mapUsers.containsKey(name)) {
                mapUsers.put(name, points);
            } else {
                int currentPoints = mapUsers.get(name);
                if (points > currentPoints) {
                    mapUsers.put(name, points);
                }
            }
            if (!mapLanguage.containsKey(language)) {
                mapLanguage.put(language, 1);
            } else {
                int currentCount = mapLanguage.get(language);
                mapLanguage.put(language, currentCount + 1);
            }
            input = scanner.nextLine();
        }
        System.out.println("Results:");
        mapUsers.forEach((key, value) -> System.out.println(key + " | " + value));
        System.out.println("Submissions:");
        mapLanguage.forEach((key, value) -> System.out.println(key + " - " + value));
    }
}
