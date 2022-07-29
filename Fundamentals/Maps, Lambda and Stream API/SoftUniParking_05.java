package Exercise_07;

import java.util.*;

public class SoftUniParking_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, String> registerMap = new LinkedHashMap<>();
        for (int i = 1; i <= n; i++) {
            String input = scanner.nextLine();
            String registerOrNot = input.split(" ")[0];
            String name = input.split(" ")[1];
            switch (registerOrNot) {
                case "register":
                    String regNumber = input.split(" ")[2];
                    if (!registerMap.containsKey(name)) {
                        registerMap.put(name, regNumber);
                        System.out.printf("%s registered %s successfully\n", name, regNumber);
                    } else {
                        System.out.printf("ERROR: already registered with plate number %s\n", regNumber);
                    }
                    break;
                case "unregister":
                    if (!registerMap.containsKey(name)) {
                        System.out.printf("ERROR: user %s not found\n", name);
                    } else {
                        registerMap.remove(name);
                        System.out.printf("%s unregistered successfully\n", name);
                    }
                    break;
            }
        }
        registerMap.entrySet().forEach(entry -> System.out.println(entry.getKey() + " => " + entry.getValue()));
    }
}
