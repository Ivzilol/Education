import java.util.ArrayList;
import java.util.List;

public class TaskExam3 {

    public static void main(String[] args) {
        TaskExam3 taskExam3 = new TaskExam3();
        taskExam3.solution("abbaa");
        taskExam3.solution("aaaa");
        taskExam3.solution("abab");
    }

    public int solution(String S) {
        int count = 0;
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            char currentLetter = S.charAt(i);
            chars.add(currentLetter);
        }
        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(0).equals(chars.get(chars.size() - 1))) {
                count++;
            }
            char firstSymbol = chars.get(0);
            chars.remove(0);
            chars.add(firstSymbol);
        }
        System.out.println(count);
        return count;
    }
}
