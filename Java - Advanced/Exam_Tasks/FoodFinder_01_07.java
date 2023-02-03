package ExamPreparetion_01;

import java.lang.reflect.Array;
import java.util.*;

public class FoodFinder_01_07 {

    private static final String FIRST_WORD = "pear";
    private static final String SECOND_WORD = "flour";
    private static final String THIRD_WORD = "pork";
    private static final String FOURTH_WORD = "olive";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputVowels = scanner.nextLine();
        String inputConsonants = scanner.nextLine();
        ArrayDeque<String> vowels = new ArrayDeque<>();
        ArrayDeque<String> consonants = new ArrayDeque<>();

        String firstWord = "pear";
        String secondWord = "flour";
        String thirdWord = "pork";
        String fourthWord = "olive";

        Arrays.stream(inputVowels.split("\\s+"))
                .forEach(vowels::offer);
        Arrays.stream(inputConsonants.split("\\s+"))
                .forEach(consonants::push);

        while (!consonants.isEmpty()) {
            String vowelsSymbol = vowels.pop();
            String consonantsSymbol = consonants.pop();
            if (firstWord.contains(vowelsSymbol)) {
                firstWord = firstWord.replace(vowelsSymbol, "");
            }
            if (firstWord.contains(consonantsSymbol)) {
                firstWord = firstWord.replace(consonantsSymbol, "");
            }
            if (secondWord.contains(vowelsSymbol)) {
                secondWord = secondWord.replace(vowelsSymbol, "");
            }
            if (secondWord.contains(consonantsSymbol)) {
                secondWord = secondWord.replace(consonantsSymbol, "");
            }
            if (thirdWord.contains(vowelsSymbol)) {
                thirdWord = thirdWord.replace(vowelsSymbol, "");
            }
            if (thirdWord.contains(consonantsSymbol)) {
                thirdWord = thirdWord.replace(consonantsSymbol, "");
            }
            if (fourthWord.contains(vowelsSymbol)) {
                fourthWord = fourthWord.replace(vowelsSymbol, "");
            }
            if (fourthWord.contains(consonantsSymbol)) {
                fourthWord = fourthWord.replace(consonantsSymbol, "");
            }
            vowels.offer(vowelsSymbol);
        }
        List<String> foundWords = new ArrayList<>();
        if (firstWord.isEmpty()) {
            foundWords.add(FIRST_WORD);
        }
        if (secondWord.isEmpty()) {
            foundWords.add(SECOND_WORD);
        }
        if (thirdWord.isEmpty()) {
            foundWords.add(THIRD_WORD);
        }
        if (fourthWord.isEmpty()) {
            foundWords.add(FOURTH_WORD);
        }

        System.out.printf("Words found: %d\n", foundWords.size());
        for (String word : foundWords) {
            System.out.println(word);
        }
    }
}
