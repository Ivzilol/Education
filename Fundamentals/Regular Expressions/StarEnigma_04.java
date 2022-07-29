package Exercise_09;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StarEnigma_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String keyRegex = "[starSTAR]";
        Pattern keyPattern = Pattern.compile(keyRegex);
        String massageRegex = "^[^@\\-!:>]*@(?<planet>[A-za-z]+)[^@\\-!:>]*:(?<population>[\\d]+)[^@\\-!:>]*!(?<attackType>[AD])![^@\\-!:>]*->(?<soldierCount>\\d+)[^@\\-!:>]*$";
        Pattern massagePattern = Pattern.compile(massageRegex);
        List<String> attacked = new LinkedList<>();
        List<String> destroyed = new LinkedList<>();
        int n = Integer.parseInt(scanner.nextLine());

        for (int index = 0; index < n; index++) {
            String massage = scanner.nextLine();
            Matcher keyMatcher = keyPattern.matcher(massage);
            int count = 0;
            while (keyMatcher.find()) {
                count++;
            }
            StringBuilder sb = new StringBuilder(massage);
            for (int indexDecrypted = 0; indexDecrypted < sb.length(); indexDecrypted++) {
                int newChar = sb.charAt(indexDecrypted) - count;
                sb.setCharAt(indexDecrypted, (char) newChar);
            }
            Matcher massageMatcher = massagePattern.matcher(sb.toString());
            while (massageMatcher.find()) {
                String planetName = massageMatcher.group("planet");
                String population = massageMatcher.group("population");
                String attackType = massageMatcher.group("attackType");
                String soldierCount = massageMatcher.group("soldierCount");
                if (attackType.equals("A")) {
                    attacked.add(planetName);
                } else {
                    destroyed.add(planetName);
                }
            }
        }
        System.out.printf("Attacked planets: %d\n", attacked.size());
        attacked.stream().sorted(String::compareTo).forEach(p -> System.out.printf("-> %s\n", p));
        System.out.printf("Destroyed planets: %d\n", destroyed.size());
        destroyed.stream().sorted(String::compareTo).forEach(p -> System.out.printf("-> %s\n", p));
    }
}
