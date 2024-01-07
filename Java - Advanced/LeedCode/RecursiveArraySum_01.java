import java.util.Arrays;
import java.util.Scanner;

public class RecursiveArraySum_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sum = getSum(arr, 0);
        System.out.println(sum);
    }

    public static int getSum(int[] arr, int index) {

        if (index == arr.length - 1) {
            return arr[index];
        }
        return arr[index] + getSum(arr, index + 1);
    }
}
