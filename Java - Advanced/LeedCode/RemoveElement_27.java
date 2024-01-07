import java.util.Scanner;

public class RemoveElement_27 {

    public int removeElement(int[] nums, int val) {
        int value = 0;

        for (final int num : nums) {
            if (num != val) {
                nums[value++] = num;
            }
        }
        return val;
    }
}
