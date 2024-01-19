import java.util.ArrayList;
import java.util.List;

public class SummaryRanges_228 {
    public static void main(String[] args) {
        SummaryRanges_228 cl = new SummaryRanges_228();
//        cl.summaryRanges(new int[]{0,1,2,4,5,7});
        System.out.println();
//        cl.summaryRanges(new int[]{0,2,3,4,6,8,9});
        cl.summaryRanges(new int[]{-1});
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 1) {
            result.add(String.valueOf(nums[0]));
        }
        int range = 1;
        boolean isInARow = true;
        for (int i = 1; i < nums.length; i++) {
            int currentNumber = nums[i];
            int previousNumber = nums[i - 1] + 1;
            if (currentNumber != previousNumber) {
                isInARow = false;
            } else {
                range++;
            }
            if (!isInARow) {
                if (range > 1) {
                    result.add(nums[i - range] + "->" + nums[i - 1]);
                } else {
                    result.add(String.valueOf(nums[i - 1]));
                }
                range = 1;
                isInARow = true;
            }
            if (i == nums.length - 1) {
                if (range > 1) {
                    result.add(nums[i - range + 1] + "->" + nums[i]);
                } else {
                    result.add(String.valueOf(nums[i]));
                }
            }
        }
        return result;
    }
}
