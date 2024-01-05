public class JumpGame_55 {
    public static void main(String[] args) {
        JumpGame_55 cl = new JumpGame_55();
        cl.canJump(new int[]{2, 3, 1, 1, 4});
//        cl.canJump(new int[]{3, 2, 1, 0, 4});
//        cl.canJump(new int[]{2, 0, 0});
//        cl.canJump(new int[]{2, 5, 0, 0});
    }

    public boolean canJump(int[] nums) {
        int reachable = 0;
        for(int i = 0; i < nums.length; i ++) {
            if(i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }
}

