import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle_120 {
    public static void main(String[] args) {
        Triangle_120 cl = new Triangle_120();
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(-1));
        input.add(Arrays.asList(2, 3));
        input.add(Arrays.asList(1,-1,-3));
//        input.add(Arrays.asList(4, 1, 8, 3));
        cl.minimumTotal(input);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int sum = 0;
        for (List<Integer> innerList : triangle) {
            int minValue = Integer.MAX_VALUE;
            for (Integer value : innerList) {
                if (value < minValue) {
                    minValue = value;
                }
            }
            sum += minValue;
        }
        return sum;
    }
}
