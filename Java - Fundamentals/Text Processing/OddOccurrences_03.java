package Lab_07;

import java.util.*;

public class OddOccurrences_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] lineInput = scanner.nextLine().split(" ");

        Map<String, Integer> wordsCountMap = new LinkedHashMap<>();
        for (int i = 0; i < lineInput.length; i++) {
            String currentWord = lineInput[i].toLowerCase();
            Integer count = wordsCountMap.get(currentWord);
            if (wordsCountMap.containsKey(currentWord)) {
                wordsCountMap.put(currentWord, count + 1);
            } else {
                wordsCountMap.put(currentWord, 1);
            }
        }
        List<String> oddWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordsCountMap.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                oddWords.add(entry.getKey());
            }
        }
        System.out.println(String.join(", ", oddWords));
    }
}
