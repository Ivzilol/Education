public class JumpGame2_45 {
    public static void main(String[] args) {
        JumpGame2_45 cl = new JumpGame2_45();
        cl.jump(new int[]{2,3,1,1,4});
    }

    public int jump(int[] nums) {
        int jumps = 0;
        int end = 0;
        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == end) {
                jumps++;
                end = farthest;
            }
        }
        System.out.println(jumps);
        return jumps;
    }
}
