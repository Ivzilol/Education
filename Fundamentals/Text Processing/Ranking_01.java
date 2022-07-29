package Traning_07;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Ranking_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputContest = scanner.nextLine();
        Map<String, String> mapContest = new LinkedHashMap<>();
        while (!inputContest.equals("end of contests")) {
            String contest = inputContest.split(":")[0];
            String passContest = inputContest.split(":")[1];
            if (!mapContest.containsKey(contest)) {
                mapContest.put(contest, passContest);
            }
            inputContest = scanner.nextLine();
        }
        String inputSubmission = scanner.nextLine();
        TreeMap<String, LinkedHashMap<String, Integer>> userMap = new TreeMap<>();
        while (!inputSubmission.equals("end of submissions")) {
            String contestUser = inputSubmission.split("=>")[0];
            String passUser = inputSubmission.split("=>")[1];
            String userName = inputSubmission.split("=>")[2];
            int points = Integer.parseInt(inputSubmission.split("=>")[3]);
            if (mapContest.containsKey(contestUser)) {
                if (mapContest.containsValue(passUser)) {
                    LinkedHashMap<String, Integer> contestResults = new LinkedHashMap<>();
                    contestResults.put(contestUser, points);
                    if (!userMap.containsKey(userName)) {
                        userMap.put(userName, contestResults);
                    } else {
                        if (!userMap.get(userName).containsKey(contestUser)) {
                            userMap.get(userName).put(contestUser, points);
                        } else {
                            userMap.get(userName).put(contestUser, Math.max(points, userMap.get(userName).get(contestUser)));
                        }
                    }
                }
            }
            inputSubmission = scanner.nextLine();
        }
        int bigPoints = 0;
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : userMap.entrySet()) {
            int sum = entry.getValue().values().stream().mapToInt(i -> i).sum();
            if (sum > bigPoints) {
                bigPoints = sum;
            }
        }
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : userMap.entrySet()) {
            if (entry.getValue().values().stream().mapToInt(i -> i).sum() == bigPoints) {
                System.out.printf("Best candidate is %s with total %d points.\n", entry.getKey(), bigPoints);
            }
        }
        System.out.println("Ranking:");
        userMap.forEach((key, value) -> {
            System.out.printf("%s%n", key);
            value.entrySet().stream().
                    sorted((f, s) -> s.getValue() - f.getValue()).
                    forEach(i -> System.out.printf("#  %s -> %d\n", i.getKey(), i.getValue()));
        });
    }
}
