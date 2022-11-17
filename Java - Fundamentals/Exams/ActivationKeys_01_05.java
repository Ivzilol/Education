package Exam_preparation_02;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ActivationKeys_01_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String code = scanner.nextLine();

        String input = scanner.nextLine();

        while (!input.equals("Generate")) {
            String commands = input.split(">>>")[0];
            switch (commands) {
                case "Slice":
                    int firstIndex = Integer.parseInt(input.split(">>>")[1]);
                    int secondIndex = Integer.parseInt(input.split(">>>")[2]);
                    String deleteText = code.substring(firstIndex, secondIndex);
                    code = code.replaceFirst(Pattern.quote(deleteText), "");
                    System.out.println(code);
                    break;
                case "Flip":
                    String upperOrLower = input.split(">>>")[1];
                    int startIndex = Integer.parseInt(input.split(">>>")[2]);
                    int endIndex = Integer.parseInt(input.split(">>>")[3]);
                    if (upperOrLower.equals("Upper")) {
                        String textChange = code.substring(startIndex, endIndex);
                        String text = code.substring(startIndex, endIndex).toUpperCase();
                        code = code.replaceFirst(Pattern.quote(textChange), text);
                        System.out.println(code);
                    } else if (upperOrLower.equals("Lower")) {
                        String textChange = code.substring(startIndex, endIndex);
                        String text = code.substring(startIndex, endIndex).toLowerCase();
                        code = code.replaceFirst(Pattern.quote(textChange), text);
                        System.out.println(code);
                    }
                    break;
                case "Contains":
                    String substring = input.split(">>>")[1];
                    if (code.contains(substring)) {
                        System.out.printf("%s contains %s\n", code, substring);
                    } else {
                        System.out.println("Substring not found!");
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println("Your activation key is: " + code);
    }
}
