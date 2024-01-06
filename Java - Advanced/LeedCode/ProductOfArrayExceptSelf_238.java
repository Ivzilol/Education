import java.awt.desktop.PrintFilesEvent;

public class ProductOfArrayExceptSelf_238 {

    public static void main(String[] args) {
        ProductOfArrayExceptSelf_238 cl = new ProductOfArrayExceptSelf_238();
        cl.productExceptSelf(new int[]{1,2,3,4});
        cl.productExceptSelf(new int[]{-1,1,0,-3,3});
    }

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int suffixProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }

        return result;
    }
}
