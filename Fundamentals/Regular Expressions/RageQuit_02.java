package Traning_09;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RageQuit_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String regex = "(?<string>[\\D]*)(?<replace>\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        StringBuilder symbols = new StringBuilder();
        while (matcher.find()) {
            int numbersReplace = Integer.parseInt(matcher.group("replace"));
            String word = matcher.group("string").toUpperCase();
            for (int indexReplace = 1; indexReplace <= numbersReplace; indexReplace++) {
                symbols.append(word);
            }
        }
        System.out.printf("Unique symbols used: %d\n", symbols.chars().distinct().count());
        System.out.print(symbols);
    }
}
