import java.util.ArrayList;
import java.util.List;

public class LongestSubstringWithoutRepeatingCharacters_3 {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters_3 cl = new LongestSubstringWithoutRepeatingCharacters_3();
        cl.lengthOfLongestSubstring("abcabcbb");
        cl.lengthOfLongestSubstring("bbbbb");
        cl.lengthOfLongestSubstring("pwwkew");
        cl.lengthOfLongestSubstring("au");
        cl.lengthOfLongestSubstring("aab");
    }

    public int lengthOfLongestSubstring(String s) {
        int answered = 0;
        for (int index = 0; index < s.length(); index++) {
            char currentChat = s.charAt(index);
            List<Character> characters = new ArrayList<>();
            characters.add(currentChat);
            for (int i = index + 1; i < s.length(); i++) {
                if (!characters.contains(s.charAt(i))) {
                    characters.add(s.charAt(i));
                } else {
                    break;
                }
            }
            if(characters.size() > answered) {
                answered = characters.size();
            }
        }
        if (s.length() == 2 && s.charAt(0) != s.charAt(1)) {
            answered = 2;
        }
        return answered;
    }
}
