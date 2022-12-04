package Exam_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageDecrypter_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int index = 0; index < n; index++) {
            String input = scanner.nextLine();
            String regex = "^([%$])([A-Z][a-z]+)\\1:\\s+\\[(?<first>\\d+)\\]\\|\\[(?<second>\\d+)\\]\\|\\[(?<third>\\d+)\\]\\|$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            List<String> list = new ArrayList<>();
            if (matcher.find()) {
                String find = matcher.group(2);
                String numbersFirst = matcher.group("first");
                String numbersSecond = matcher.group("second");
                String numbersThird = matcher.group("third");
                list.add(find);
                list.add(numbersFirst);
                list.add(numbersSecond);
                list.add(numbersThird);
                String tag = list.get(0);
                StringBuilder currentMessage = new StringBuilder();
                for (int IndexList = 1; IndexList < list.size(); IndexList++) {
                    int currentNumber = Integer.parseInt(list.get(IndexList));
                    char currentChar = (char) currentNumber;
                    currentMessage.append(currentChar);
                }
                System.out.printf("%s: %s\n", tag, currentMessage.toString());
            } else {
                System.out.println("Valid message not found!");
            }
        }
    }
}
