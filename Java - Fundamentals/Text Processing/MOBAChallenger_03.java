package Traning_07;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MOBAChallenger_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, LinkedHashMap<String, Integer>> playersMap = new LinkedHashMap<>();

        //

        while (!input.equals("Season end")) {
            if (input.contains(" -> ")) {
                String namePlayer = input.split(" -> ")[0];
                String position = input.split(" -> ")[1];
                int skill = Integer.parseInt(input.split(" -> ")[2]);
                if (!playersMap.containsKey(namePlayer)) {
                    playersMap.put(namePlayer, new LinkedHashMap<>());
                    playersMap.get(namePlayer).put(position, skill);
                } else {
                    if (playersMap.get(namePlayer).containsKey(position)) {
                        if (playersMap.get(namePlayer).get(position) < skill) {
                            playersMap.get(namePlayer).put(position, skill);
                        }
                    } else {
                        playersMap.get(namePlayer).put(position, skill);
                    }
                }
            }
            if (input.contains("vs")) {
                String firsPlayer = input.split(" vs ")[0];
                String secondPlayer = input.split(" vs ")[1];
                if (playersMap.containsKey(firsPlayer) && playersMap.containsKey(secondPlayer)) {
                    boolean isEquals = false;
                    for (String p1 : playersMap.get(firsPlayer).keySet()) {
                        for (String p2 : playersMap.get(secondPlayer).keySet()) {
                            if (p1.equals(p2)) {
                                isEquals = true;
                                break;
                            }
                        }
                    }
                    if (isEquals) {
                        if (playersMap.get(firsPlayer).values().stream().mapToInt(i -> i).sum() >
                                playersMap.get(secondPlayer).values().stream().mapToInt(i -> i).sum()) {
                            playersMap.remove(secondPlayer);
                        } else if (playersMap.get(firsPlayer).values().stream().mapToInt(i -> i).sum() <
                                playersMap.get(secondPlayer).values().stream().mapToInt(i -> i).sum()) {
                            playersMap.remove(firsPlayer);
                        }
                    }
                }
            }
            input = scanner.nextLine();
        }
        playersMap.entrySet().stream().sorted((p1, p2) -> {
                    int sort = Integer.compare(p2.getValue().values().stream().mapToInt(i -> i).sum(),
                            p1.getValue().values().stream().mapToInt(i -> i).sum());
                    if (sort == 0) {
                        sort = p1.getKey().compareTo(p2.getKey());
                    }
                    return sort;
                })
                .forEach(entry -> {
                    System.out.printf("%s: %d skill\n", entry.getKey(), entry.getValue().values().stream().
                            mapToInt(i -> i).sum());
                    entry.getValue().entrySet().stream().sorted((e1, e2) -> {
                        int sort = Integer.compare(e2.getValue(), e1.getValue());
                        if (sort == 0) {
                            sort = e1.getKey().compareTo(e2.getKey());
                        }
                        return sort;
                    }).forEach(e -> {
                        System.out.printf("- %s <::> %d\n", e.getKey(), e.getValue());
                    });
                });
    }
}
