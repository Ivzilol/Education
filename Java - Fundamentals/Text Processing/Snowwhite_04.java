package Traning_07;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Snowwhite_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<String, Integer> physicNameAndColor = new LinkedHashMap<>();
        Map<String, Integer> countByColor = new LinkedHashMap<>();

        while (!input.equals("Once upon a time")) {
            String nameDwarf = input.split(" <:> ")[0];
            String hatColor = input.split(" <:> ")[1];
            int physics = Integer.parseInt(input.split(" <:> ")[2]);
            countByColor.putIfAbsent(hatColor, 0);
            String nameAndColor = hatColor + ":" + nameDwarf;
            if (physicNameAndColor.containsKey(nameAndColor)) {
                if (physicNameAndColor.get(nameAndColor) < physics) {
                    physicNameAndColor.put(nameAndColor, physics);
                }
            } else {
                physicNameAndColor.put(nameAndColor, physics);
                countByColor.put(hatColor, countByColor.get(hatColor) + 1);
            }
            input = scanner.nextLine();
        }
        physicNameAndColor.entrySet().stream().sorted(
                (e1, e2) -> {
                    Integer physics2 = e2.getValue();
                    Integer physics1 = e1.getValue();
                    if (Integer.compare(physics1, physics2) == 0) {
                        String key1 = e1.getKey();
                        String key2 = e2.getKey();
                        String color1 = key1.split(":")[0];
                        String color2 = key2.split(":")[0];
                        Integer count1 = countByColor.get(color1);
                        Integer count2 = countByColor.get(color2);
                        return Integer.compare(count2, count1);
                    }
                    return Integer.compare(physics2, physics1);
                }
        ).forEach(
                e -> {
                    String[] colorAndName = e.getKey().split(":");
                    Integer physics = e.getValue();

                    System.out.printf("(%s) %s <-> %d\n", colorAndName[0], colorAndName[1], physics);
                }
        );
    }
}
