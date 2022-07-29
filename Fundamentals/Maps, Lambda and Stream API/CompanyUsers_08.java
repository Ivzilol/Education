package Exercise_07;

import java.util.*;

public class CompanyUsers_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, List<String>> mapCompany = new LinkedHashMap<>();
        while (!input.equals("End")) {
            String company = input.split(" -> ")[0];
            String id = input.split(" -> ")[1];
            if (!mapCompany.containsKey(company)) {
                mapCompany.put(company, new ArrayList<>());
                mapCompany.get(company).add(id);
            } else {
                List<String> currentId = mapCompany.get(company);
                if (!currentId.contains(id)) {
                    currentId.add(id);
                    mapCompany.put(company, currentId);
                }
            }
            input = scanner.nextLine();
        }
        mapCompany.entrySet().stream().forEach(entry ->
        {
            System.out.printf("%s\n", entry.getKey());
            entry.getValue().forEach(id -> System.out.println("-- " + id));
        });
    }
}
