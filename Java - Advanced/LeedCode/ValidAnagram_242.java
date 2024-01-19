import java.util.Arrays;

public class ValidAnagram_242 {
    public static void main(String[] args) {
        ValidAnagram_242 cl = new ValidAnagram_242();
        cl.isAnagram("anagram", "nagaram");
        cl.isAnagram("rat", "car");
    }

    public boolean isAnagram(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        String sortedS = new String(sChars);
        String sortedT = new String(tChars);

        return sortedS.equals(sortedT);
    }
}
