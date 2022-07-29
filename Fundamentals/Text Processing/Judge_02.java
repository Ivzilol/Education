package Traning_07;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Judge_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<String, LinkedHashMap<String, Integer>> mapContest = new LinkedHashMap<>();
        while (!input.equals("no more time")) {
            String username = input.split(" -> ")[0];
            String contest = input.split(" -> ")[1];
            int points = Integer.parseInt(input.split(" -> ")[2]);
            if (!mapContest.containsKey(contest)) {
                mapContest.put(contest, new LinkedHashMap<>());
                mapContest.get(contest).put(username, points);
            } else {
                if (mapContest.get(contest).containsKey(username)) {
                    if (mapContest.get(contest).get(username) < points) {
                        mapContest.get(contest).put(username, points);
                    }
                } else {
                    mapContest.get(contest).put(username, points);
                }
            }
            input = scanner.nextLine();
        }
        AtomicInteger number = new AtomicInteger();
        mapContest.forEach((key, value) -> {
            System.out.printf("%s: %d participants\n", key, value.size());
            number.set(1);
            Map<String, Integer> studentsRow = new HashMap<>();
            studentsRow = value;
            studentsRow.entrySet().stream().sorted((e1, e2) -> {
                if (Objects.equals(e2.getValue(), e1.getValue())) {
                    return e1.getKey().compareTo(e2.getKey());
                } else {
                    return e2.getValue() - e1.getValue();
                }
            }).forEach((s) -> System.out.printf("%d. %s <::> %d\n", number.getAndIncrement(), s.getKey(), s.getValue()));
        });
        Map<String, Integer> finalStandings = new TreeMap<>();
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : mapContest.entrySet()) {
            for (Map.Entry<String, Integer> go : entry.getValue().entrySet()) {
                finalStandings.putIfAbsent(go.getKey(), 0);
                finalStandings.put(go.getKey(), finalStandings.get(go.getKey()) + go.getValue());
            }
        }
        System.out.println("Individual standings:");
        number.set(1);
        finalStandings.entrySet().stream().sorted((e2, e1) -> {
            int sort = Integer.compare(e1.getValue(), e2.getValue());
            if (sort == 0) {
                sort = e2.getKey().compareTo(e1.getKey());
            }
            return sort;
        }).forEach(e -> {
            System.out.printf("%d. %s -> %d\n", number.getAndIncrement(), e.getKey(), e.getValue());
        });
    }
}
