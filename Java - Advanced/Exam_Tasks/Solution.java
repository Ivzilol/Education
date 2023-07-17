import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();

        System.out.println(maximumOccurringCharacter(word));
    }

    public static char maximumOccurringCharacter(String text) {
        int count = 0;
        int mostAppearsCharCount = 0;
        char mostAppearsChar = ' ';
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < text.length(); j++) {
                if (text.charAt(i) == text.charAt(j)) {
                    count++;
                }
            }
            if (count > mostAppearsCharCount) {
                mostAppearsCharCount = count;
                mostAppearsChar = text.charAt(i);
            }
            count = 0;
        }
        return mostAppearsChar;
    }
}