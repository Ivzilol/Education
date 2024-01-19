public class MinimumSizeSubarraySum_209 {
    public static void main(String[] args) {
        MinimumSizeSubarraySum_209 cl = new MinimumSizeSubarraySum_209();
        cl.minSubArrayLen(7, new int[]{2,3,1,2,4,3});
        cl.minSubArrayLen(4, new int[]{1,4,4});
        cl.minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1});
        cl.minSubArrayLen(11, new int[]{1,2,3,4,5});
        cl.minSubArrayLen(15, new int[]{5,1,3,5,10,7,4,9,2,8});
        cl.minSubArrayLen(20, new int[]{2,16,14,15});
        cl.minSubArrayLen(213, new int[]{12,28,83,4,25,26,25,2,25,25,25,12});
    }

    public int minSubArrayLen(int target, int[] nums) {
        int minArr = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        boolean isHaveMinArr = false;
        for (int index = 0; index < nums.length; index++) {
            sum += nums[index];
            while (sum >= target) {
                isHaveMinArr = true;
                sum -= nums[left];
                minArr = Math.min(minArr, index - left + 1);
                left++;
            }
        }
        if (!isHaveMinArr) {
            minArr = 0;
        }
        System.out.println(minArr);
        return minArr;
    }
}
