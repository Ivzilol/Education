import java.util.ArrayList;
import java.util.List;

public class RansomNote_383 {
    public static void main(String[] args) {
        RansomNote_383 cl = new RansomNote_383();
        cl.canConstruct("a", "b");
        cl.canConstruct("aa", "ab");
        cl.canConstruct("aa", "aab");
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        boolean answered = false;
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        List<String> note = new ArrayList<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            char currentSymbol = ransomNote.charAt(i);
            note.add(String.valueOf(currentSymbol));
        }
        List<String> mag = new ArrayList<>();
        for (int i = 0; i < magazine.length(); i++) {
            char currentSymbol = magazine.charAt(i);
            mag.add(String.valueOf(currentSymbol));
        }
        for (int i = 0; i < note.size(); i++) {
            String currentNote = note.get(i);
            if (mag.contains(currentNote)) {
                note.remove(currentNote);
                mag.remove(currentNote);
                i--;
            }
        }
        if (note.isEmpty()) {
            answered = true;
        }
        System.out.println(answered);
        return answered;
    }
}
