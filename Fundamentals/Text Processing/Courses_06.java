package Exercise_07;

import java.util.*;

public class Courses_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<String, List<String>> mapProgram = new LinkedHashMap<>();
        while (!input.equals("end")) {
            String programName = input.split(" : ")[0];
            String userName = input.split(" : ")[1];
            if (!mapProgram.containsKey(programName)) {
                mapProgram.put(programName, new ArrayList<>());
                mapProgram.get(programName).add(userName);
            } else {
                List<String> currentUser = mapProgram.get(programName);
                if (!currentUser.contains(userName)) {
                    currentUser.add(userName);
                    mapProgram.put(programName, currentUser);
                }
            }
            input = scanner.nextLine();
        }
        mapProgram.entrySet().stream().forEach(entry ->
        {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue().size());
            entry.getValue().forEach(userName -> System.out.println("-- " + userName));
        });
    }
}
