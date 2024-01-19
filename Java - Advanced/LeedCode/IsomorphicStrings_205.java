import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IsomorphicStrings_205 {
    public static void main(String[] args) {
        IsomorphicStrings_205 cl = new IsomorphicStrings_205();
        cl.isIsomorphic("egg", "add");
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sCharacter = s.charAt(i);
            char tCharacter = t.charAt(i);
            if (map1.containsKey(sCharacter) || map2.containsKey(tCharacter)) {
                if (!Objects.equals(map1.get(sCharacter), map2.get(tCharacter))) {
                    return false;
                }
            } else {
                map1.put(sCharacter, i);
                map2.put(tCharacter, i);
            }
        }

        return true;
    }
}
