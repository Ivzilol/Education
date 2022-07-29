package Lab_07;

import java.util.*;

public class WordSynonyms_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<String>> wordsMap = new LinkedHashMap<>();
        for (int index = 1; index <= n; index++) {
            String word = scanner.nextLine();
            String synonym = scanner.nextLine();

            if (!wordsMap.containsKey(word)) {
                wordsMap.put(word, new ArrayList<>());
                List<String> stringList = wordsMap.get(word);
                wordsMap.get(word).add(synonym);
            } else {
                wordsMap.get(word).add(synonym);
            }
        }
        for (Map.Entry<String, List<String>> entry : wordsMap.entrySet()) {
            System.out.printf("%s - %s\n", entry.getKey(), String.join(", ", entry.getValue()));
        }
    }
}
