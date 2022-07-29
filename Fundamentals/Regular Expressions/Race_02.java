package Exercise_09;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class    Race_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nameRegex = "[A-Za-z]+";
        String distance = "[\\d]{1}";

        List<String> names = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());
        Map<String, Integer> racers = new LinkedHashMap<>();
        for (String name : names) {
            racers.putIfAbsent(name, 0);

        }
        Pattern namePattern = Pattern.compile(nameRegex);
        Pattern distancePattern = Pattern.compile(distance);
        String input = scanner.nextLine();

        while (!input.equals("end of race")) {
            Matcher nameMatcher = namePattern.matcher(input);
            StringBuilder sb = new StringBuilder();
            while (nameMatcher.find()) {
                sb.append(nameMatcher.group());
            }
            if (racers.containsKey(sb.toString())) {
                Matcher distanceMatcher = distancePattern.matcher(input);
                while (distanceMatcher.find()) {
                    int digit = Integer.parseInt(distanceMatcher.group());
                    racers.put(sb.toString(), racers.get(sb.toString()) + digit);
                }
            }
            input = scanner.nextLine();
        }
        List<String> sorted = racers.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .map(Map.Entry::getKey).collect(Collectors.toList());
        String text = "";
        for (int i = 0; i < sorted.size(); i++) {
            if (i == 3) {
                break;
            }
            if (i == 0) {
                text = "st";
            } else if (i == 1) {
                text = "nd";
            } else if (i == 2) {
                text = "rd";
            }
            System.out.printf("%d%s place: %s\n", i + 1, text, sorted.get(i));
        }
    }
}
