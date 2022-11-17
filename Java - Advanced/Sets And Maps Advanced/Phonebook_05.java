package Exercises_03;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, String> phoneMap = new LinkedHashMap<>();
        while (!input.equals("search")) {
            String name = input.split("-")[0];
            String phoneNumber = input.split("-")[1];
            if (!phoneMap.containsKey(name)) {
                phoneMap.put(name, phoneNumber);
            } else {
                phoneMap.put(name, phoneNumber);
            }
            input = scanner.nextLine();
        }
        String name = scanner.nextLine();
        while (!name.equals("stop")) {
            if (phoneMap.containsKey(name)) {
                System.out.println(name + " -> " + phoneMap.get(name));
            } else {
                System.out.printf("Contact %s does not exist.\n", name);
            }
            name = scanner.nextLine();
        }
    }
}
