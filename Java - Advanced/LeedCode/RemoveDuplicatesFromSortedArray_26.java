public class RemoveDuplicatesFromSortedArray_26 {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray_26 cl = new RemoveDuplicatesFromSortedArray_26();
        cl.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
    }

    public int removeDuplicates(int[] nums) {
        int k = 0;
        for ( int num : nums) {
            if (k < 1 || num > nums[k - 1]) {
                nums[k++] = num;
            }
        }
        System.out.println(k);
        return k;
    }
}
