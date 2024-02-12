package Workshop_03;

import java.util.HashSet;

public class Demo1 {

    public static void main(String[] args) {
        Demo1 cl = new Demo1();
        cl.solution(new int[]{1, 3, 6, 4, 1, 2});
    }




    public int solution(int[] A) {

        HashSet<Integer> positiveNumbers = new HashSet<>();
        for (int j : A) {
           if (j > 0) {
               positiveNumbers.add(j);
           }
        }
        int answered = 1;
        while (true) {
            if (!positiveNumbers.contains(answered)) {
                System.out.println(answered);
                return answered;
            }
            answered++;
        }
    }
}
