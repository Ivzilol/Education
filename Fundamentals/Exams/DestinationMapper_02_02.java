package Exam_preparation_02;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationMapper_02_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String regex = "([=/])([A-Z][A-Za-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> listTravels = new LinkedList<>();
        int travelPoints = 0;
        while (matcher.find()) {
            String currentDestination = matcher.group(2);
            travelPoints += matcher.group(2).length();
            listTravels.add(currentDestination);
        }
        if (travelPoints > 0) {
            System.out.print("Destinations: ");
        } else {
            System.out.println("Destinations:");
        }
        for (int index = 0; index < listTravels.size(); index++) {
            String currentWord = listTravels.get(index);
            if (index != listTravels.size() - 1) {
                System.out.printf("%s, ", currentWord);
            } else {
                System.out.println(currentWord);
            }
        }
        System.out.printf("Travel Points: %d", travelPoints);
    }
}
