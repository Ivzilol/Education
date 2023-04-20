package Survivor;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ElfWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        simple input
//        c o d e w a r s
//        h a c k e r r a n k


        List<String> firstWorInput = Arrays.stream(scanner.nextLine().split(" ")).toList();
        List<String> secondWorInput = Arrays.stream(scanner.nextLine().split(" ")).toList();
        StringBuilder firstWord = new StringBuilder();
        StringBuilder secondWord = new StringBuilder();
        for (String s : firstWorInput) {
            firstWord.append(s);
        }
        for (String s : secondWorInput) {
            secondWord.append(s);
        }
        printResult(firstWord, secondWord);
    }

    private static void printResult(StringBuilder firstWord, StringBuilder secondWord) {
        int count = 0;
        for (int i = 0; i < firstWord.length(); i++) {
            char currentCharFirstWord = firstWord.charAt(i);
            for (int j = 0; j < secondWord.length(); j++) {
                char currentCharSecondWord = secondWord.charAt(j);
                if(currentCharFirstWord == currentCharSecondWord) {
                    count++;
                    break;
                }
            }
        }
        System.out.println(firstWord.length() + secondWord.length() - (count * 2));
    }
}
