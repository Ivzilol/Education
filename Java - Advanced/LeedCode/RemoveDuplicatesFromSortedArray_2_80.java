public class RemoveDuplicatesFromSortedArray_2_80 {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray_2_80 cl = new RemoveDuplicatesFromSortedArray_2_80();
        cl.removeDuplicates(new int[]{1,1,1,2,2,3});
    }


    public int removeDuplicates(int[] nums) {
        int k = 0;
        for (int currentNum : nums) {
            if (k < 2 || currentNum > nums[k -2]) {
                nums[k] = currentNum;
                k++;
            }
        }
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        System.out.println(k);
        return k;
    }
}
