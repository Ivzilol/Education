package Exam_preparation_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector_02_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String regexNumbers = "[0-9]";
        Pattern patternNumbers = Pattern.compile(regexNumbers);
        Matcher matcherNumbers = patternNumbers.matcher(input);
        int result = 1;
        while (matcherNumbers.find()) {
            int currentNumber = Integer.parseInt(matcherNumbers.group());
            result *= currentNumber;
        }
        String regexEmoji = ":{2}([A-Z][a-z]{2,}):{2}|\\*{2}([A-Z][a-z]{2,})\\*{2}";
        Pattern patternEmoji = Pattern.compile(regexEmoji);
        Matcher matcherEmoji = patternEmoji.matcher(input);
        List<String> coolEmoji = new ArrayList<>();
        int countEmoji = 0;
        while (matcherEmoji.find()) {
            int sumCharacter = 0;
            String currentWord = matcherEmoji.group();
            countEmoji++;
            for (int index = 0; index < currentWord.length(); index++) {
                char currentChar = currentWord.charAt(index);
                if (Character.isLetter(currentChar)) {
                    sumCharacter += currentChar;
                }
            }
            if (sumCharacter > result) {
                coolEmoji.add(currentWord);
            }
        }
        System.out.printf("Cool threshold: %d\n", result);
        System.out.printf("%d emojis found in the text. The cool ones are:\n", countEmoji);
        for (String word : coolEmoji) {
            System.out.println(word);
        }
    }
}
