package Exam_preparation_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdAstra_02_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String regex = "(#|\\|)(?<items>[A-Za-z ]+)\\1(?<term>\\d{2}\\/\\d{2}\\/\\d{2})\\1(?<calories>\\d{1,5})\\1";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> items = new ArrayList<>();
        double totalCalories = 0;
        while (matcher.find()) {
            String product = matcher.group("items");
            String term = matcher.group("term");
            double calories = Double.parseDouble(matcher.group("calories"));
            int intCalories = (int) calories;
            totalCalories += intCalories;
            items.add(product);
            items.add(term);
            items.add(String.valueOf(intCalories));
        }
        double dayCalories = Math.floor(totalCalories / 2000);
        System.out.printf("You have food to last you for: %.0f days!\n", dayCalories);
        int count = 0;
        for (String item : items) {
            count++;
            if (count == 1) {
                System.out.printf("Item: %s, ", item);
            }
            if (count == 2) {
                System.out.printf("Best before: %s, ", item);
            }
            if (count == 3) {
                System.out.printf("Nutrition: %s ", item);
                count = 0;
                System.out.println();
            }
        }
    }
}
