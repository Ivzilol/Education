package Exam_preparation_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MirrorWords_02_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String regex = "\\#{1}[A-z]{3,}\\#{2}[A-z]{3,}\\#{1}|\\@{1}[A-z]{3,}\\@{2}[A-z]{3,}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> wordPairs = new ArrayList<>();
        while (matcher.find()) {
            wordPairs.add(matcher.group());
        }
        List<String> words = new ArrayList<>();
        for (String word : wordPairs) {
            StringBuilder newWord = new StringBuilder();
            for (int index = 0; index < word.length(); index++) {
                char currentChar = word.charAt(index);
                if (Character.isLetter(currentChar)) {
                    newWord.append(currentChar);
                }
            }
            words.add(String.valueOf(newWord));
        }
        List<String> finalList = new ArrayList<>();
        for (String word : words) {
            String firstWord = word.substring(0, word.length() / 2);
            String secondWord = word.substring(word.length() / 2);
            String firstWordToPrint = "";
            StringBuilder secondWordToPrint = new StringBuilder();
            firstWordToPrint = firstWord;
            secondWordToPrint.append(secondWord);
            secondWordToPrint = new StringBuilder(secondWordToPrint.reverse().toString());
            String second = String.valueOf(secondWordToPrint);
            if (firstWordToPrint.equals(second)) {
                finalList.add(firstWord);
                finalList.add(secondWord);
            }
        }
        if (words.isEmpty()) {
            System.out.println("No word pairs found!");
            System.out.println("No mirror words!");
            return;
        }
        System.out.printf("%d word pairs found!\n", words.size());
        if (finalList.isEmpty()) {
            System.out.println("No mirror words!");
            return;
        }
        System.out.println("The mirror words are:");
        for (int index = 0; index < finalList.size(); index++) {
            String currentWord = finalList.get(index);
            if (index == finalList.size() - 1) {
                System.out.print(currentWord);
                break;
            }
            if (index % 2 == 0) {
                System.out.print(currentWord + " <=> ");
            } else {
                System.out.print(currentWord + ", ");
            }
        }
    }
}
