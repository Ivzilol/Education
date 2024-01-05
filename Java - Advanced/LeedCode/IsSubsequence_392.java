import java.util.Objects;

public class IsSubsequence_392 {

    public static void main(String[] args) {
        IsSubsequence_392 cl = new IsSubsequence_392();
        cl.isSubsequence("abc", "ahbgdc");
        cl.isSubsequence("axc", "ahbgdc");
        cl.isSubsequence("", "");
        cl.isSubsequence("ab", "baab");
        cl.isSubsequence("acb", "ahbgdc");
        cl.isSubsequence("aaaaaa", "bbaaaa");
    }

    public boolean isSubsequence(String s, String t) {
        StringBuilder sb = new StringBuilder();
        if (Objects.equals(s, "")) {
            return true;
        }
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            for (int j = index + 1; j < t.length(); j++) {
                char charT = t.charAt(j);
                if (charS == charT) {
                    sb.append(charS);
                    index = j;
                    break;
                }
            }
            if (String.valueOf(sb).equals(s)) {
                return true;
            }
        }
        return false;
    }
}
