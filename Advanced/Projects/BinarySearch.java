import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);

        String[] numbersInString = input.nextLine().split(" ");
        int numberToSearch = Integer.parseInt(input.nextLine());

        int[] numbers = new int[numbersInString.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersInString[i]);
        }

        int startIndex = 0;
        int endIndex = numbers.length - 1;
        System.out.println(performBinarySearch(numbers, numberToSearch, startIndex, endIndex));
    }

    private static int performBinarySearch(int[] numbers, int numberToSearch, int startIndex, int endIndex) {
        int middleIndex = (startIndex + endIndex) / 2;
        if (startIndex > endIndex) {
            return -1;
        }

        if (numbers[middleIndex] == numberToSearch) {
            // if we enter the loop this means, that the are two or more numbers equal to the number we are searching for, and we have to take the first of them.
            while (middleIndex - 1 >= 0 && numbers[middleIndex - 1] == numberToSearch) {
                middleIndex--;
            }
            return middleIndex;
        } else if (numberToSearch < numbers[middleIndex]) {
            return performBinarySearch(numbers, numberToSearch, startIndex, middleIndex - 1);
        } else {
            return performBinarySearch(numbers, numberToSearch, middleIndex + 1, endIndex);
        }
    }
}
