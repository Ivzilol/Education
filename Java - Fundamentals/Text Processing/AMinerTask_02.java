package Exercise_07;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AMinerTask_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String resource = scanner.nextLine();

        Map<String, Integer> resourceMap = new LinkedHashMap<>();

        while (!resource.equals("stop")) {
            int quantity = Integer.parseInt(scanner.nextLine());
            if (!resourceMap.containsKey(resource)) {
                resourceMap.put(resource, quantity);
            } else {
                int currentMaterial = resourceMap.get(resource);
                resourceMap.put(resource, currentMaterial + quantity);
            }
            resource = scanner.nextLine();
        }
        resourceMap.entrySet().forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));
    }
}
