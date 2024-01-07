public class SortArray {

    private static int[] sortArr(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Размяна на текущия елемент с най-малкия намерен
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {

        int[] array = {7, 2, 10, 5, 1, 8};

        int[] sortedArr = sortArr(array);
        for (int j : sortedArr) {
            System.out.print(j + " ");
        }
    }
}
