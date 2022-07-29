package Traning_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PostOffice_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> input = Arrays.stream(scanner.nextLine().split("\\|")).collect(Collectors.toList());
        StringBuilder firstWord = new StringBuilder();
        List<String> secondWord = new ArrayList<>();

        for (String currentWord : input) {
            String regexFirstWord = "([#$%*&])([A-Z]+)\\1";
            Pattern patternFirstWord = Pattern.compile(regexFirstWord);
            Matcher matcherFirstWord = patternFirstWord.matcher(currentWord);
            while (matcherFirstWord.find()) {
                firstWord.append(matcherFirstWord.group());
            }
            String regexSecondWord = "[0-9]{2}\\:[0-9]{2}";
            Pattern patternSecondWord = Pattern.compile(regexSecondWord);
            Matcher matcherSecondWord = patternSecondWord.matcher(currentWord);
            while (matcherSecondWord.find()) {
                secondWord.add(matcherSecondWord.group());
            }
        }
        String thirdWord = input.get(2);
        List<String> listThirdWord = List.of(thirdWord.split("\\s+"));

        String firstWordSub = firstWord.substring(1, firstWord.length() - 1);
        List<String> printList = new ArrayList<>();
        int indexListLength = -1;
        for (int indexFirstWord = 0; indexFirstWord < firstWordSub.length(); indexFirstWord++) {
            boolean isFiend = false;
            char currentChar = firstWordSub.charAt(indexFirstWord);
            indexListLength += 1;
            for (int indexList = indexListLength; indexList < secondWord.size(); indexList++) {
                if (isFiend) {
                    break;
                }
                String currentIndex = secondWord.get(indexList);
                List<String> codeAndLengthWord = List.of(currentIndex.split(":"));
                int codWord = Integer.parseInt(codeAndLengthWord.get(0));
                int lengthWord = Integer.parseInt(codeAndLengthWord.get(1));
                for (int indexThirdWord = 0; indexThirdWord < listThirdWord.size(); indexThirdWord++) {
                    String currentWord = listThirdWord.get(indexThirdWord);
                    char firstChar = currentWord.charAt(0);
                    if (firstChar == currentChar && currentWord.length() == lengthWord + 1 && currentWord.length() <= 20) {
                        System.out.println(currentWord);
                        isFiend = true;
                        break;
                    }
                }
            }
        }
    }
}
