import java.util.Arrays;

class MergeSortedArray_88 {
    public static void main(String[] args) {
        MergeSortedArray_88 solution = new MergeSortedArray_88();
        solution.merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{4, 5, 6}, 3);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] margeArr = new int[m + n];
        int index = 0;
        for (int value : nums1) {
            if (value == 0) {
                continue;
            }
            margeArr[index++] = value;
        }
        for (int value : nums2) {
            if (value == 0) {
                continue;
            }
            margeArr[index++] = value;
        }
        sortArray(margeArr);
        System.out.println(Arrays.toString(margeArr));
    }

    private static void sortArray(int[] margeArr) {
        mergeSort(margeArr, 0, margeArr.length - 1);
    }

    private static void mergeSort(int[] margeArr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(margeArr, left, middle);
            mergeSort(margeArr, middle + 1, right);

            mergeTwoArr(margeArr, left, middle, right);
        }
    }

    private static void mergeTwoArr(int[] margeArr, int left, int middle, int right) {
        method(margeArr, left, middle, right);
    }

    static void method(int[] margeArr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = margeArr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = margeArr[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                margeArr[k] = leftArray[i];
                i++;
            } else {
                margeArr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            margeArr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            margeArr[k] = rightArray[j];
            j++;
            k++;
        }
    }
}


