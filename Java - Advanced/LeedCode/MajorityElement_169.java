public class MajorityElement_169 {
    public static void main(String[] args) {
        MajorityElement_169 cl = new MajorityElement_169();
        System.out.println(cl.majorityElement(new int[]{2,2,1,1,1,2,2}));
    }



    public int majorityElement(int[] nums) {
        Integer ans = null;
        int count = 0;

        for (final int num : nums) {
            if (count == 0) {
                ans = num;
            }
            count += num == ans ? 1 : -1;
        }
        return ans;
    }
}
