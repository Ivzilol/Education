package Lab_07;

import java.util.Arrays;
import java.util.Scanner;

public class WordFilter_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] wordsArr = Arrays.stream(scanner.nextLine().split(" "))
                .filter(e -> e.length()% 2 ==0).toArray(String[]::new);

        System.out.println(String.join(System.lineSeparator(), wordsArr));
    }
}
