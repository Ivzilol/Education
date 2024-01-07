import java.util.Arrays;

public class RotateArray_189 {
    public static void main(String[] args) {
        RotateArray_189 cl = new RotateArray_189();
//        cl.rotate(new int[]{-1, -100, 3, 99}, 2);
        cl.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
//        cl.rotate(new int[]{1}, 1);
//        cl.rotate(new int[]{1, 2}, 3);
        cl.rotate(new int[]{1, 2, 3}, 2);
    }

    public void rotate(int[] nums, int k) {
        if (nums.length <= 1) {
            return;
        }
        if (nums.length < k) {
            int start = 0;
            int end = nums.length - 1;
            int temp;
            while (start < end) {
                temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
            System.out.print(Arrays.toString(nums));
            return;
        }
        if (k == 0) {
            return;
        }
        int[] firstArr = new int[k];
        int[] secondArr = new int[nums.length - k];
        if (nums.length % 2 != 0) {
            firstArr = new int[k + 1];
            secondArr = new int[nums.length - k - 1];
        }
        int count = 0;
        for (int i = 0; i <= nums.length - 1; i++) {
            if (nums.length % 2 != 0) {
                if (count <= k) {
                    firstArr[i] = nums[i];
                    count++;
                } else {
                    break;
                }
            } else {
                if (count < k) {
                    firstArr[i] = nums[i];
                    count++;
                } else {
                    break;
                }
            }
        }
        int count2 = k;
        for (int i = k; i <= nums.length; i++) {
            if (nums.length % 2 == 0) {
                if (count2 <= nums.length - 1) {
                    secondArr[i - k] = nums[i];
                    count2++;
                } else {
                    break;
                }
            } else {
                if (count2 < nums.length - 1) {
                    secondArr[i - k] = nums[i + 1];
                    count2++;
                } else {
                    break;
                }
            }
        }
        System.arraycopy(secondArr, 0, nums, 0, secondArr.length - 1 + 1);
        System.arraycopy(firstArr, 0, nums, secondArr.length, firstArr.length - 1 + 1);
        System.out.print(Arrays.toString(nums));
    }
}
