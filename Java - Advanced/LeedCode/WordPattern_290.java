import java.util.*;
import java.util.stream.Collectors;

public class WordPattern_290 {
    public static void main(String[] args) {
        WordPattern_290 cl = new WordPattern_290();
        cl.wordPattern("abba", "dog cat cat dog");
        cl.wordPattern("abba", "dog cat cat fish");
        cl.wordPattern("aaaa", "dog cat cat dog");
        cl.wordPattern("aaa", "aa aa aa aa");
        cl.wordPattern("jquery", "jquery");

    }

    public boolean wordPattern(String pattern, String s) {
        boolean isPattern = true;
        List<String> words = Arrays.stream(s.split("\\s+")).toList();
        Map<Character, Integer> patternMap = new HashMap<>();
        Map<String, Integer> sMap = new HashMap<>();
        if (words.size() != pattern.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char patternSymbol = pattern.charAt(i);
            String word = words.get(i);
            if (patternMap.containsKey(patternSymbol) || sMap.containsKey(word)) {
                if (!Objects.equals(patternMap.get(patternSymbol), sMap.get(word))) {
                    isPattern = false;
                    break;
                }
            } else {
                patternMap.put(patternSymbol, i);
                sMap.put(word, i);
            }
        }
        return isPattern;
    }
}
