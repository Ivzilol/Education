import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {

        Task1 task1 = new Task1();
        task1.solution(new int[]{1, 3, 6, 4, 1, 2});
        task1.solution(new int[]{1, 2, 3});
        task1.solution(new int[]{-1, -3});
    }

    public int solution(int[] A) {
        int answered = 1;
        List<Integer> sortArr = new ArrayList<>();
        for (Integer currentNumber : A) {
            if (currentNumber <= 0) {
                continue;
            }
            sortArr.add(currentNumber);
        }
        Collections.sort(sortArr);
        boolean not = false;
        int last = 0;
        for (int i = 1; i < sortArr.size(); i++) {
            int currentNumber = sortArr.get(i);
            int prevNumber = sortArr.get(i - 1);
            last = currentNumber;
            if (currentNumber != prevNumber && currentNumber - 1 != prevNumber) {
                answered = currentNumber - 1;
                not = true;
                break;
            }
        }
        if (!not) {
            answered = last + 1;
        }
        return answered;
    }
}
