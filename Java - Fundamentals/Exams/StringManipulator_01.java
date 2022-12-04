package Exam_02;

import java.util.Scanner;
import java.util.regex.Pattern;

public class StringManipulator_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String message = scanner.nextLine();
        String inputString = scanner.nextLine();
        while (!inputString.equals("End")) {
            switch (inputString.split("\\s+")[0]) {
                case "Translate":
                    String oldChar = inputString.split("\\s+")[1];
                    String newChar = inputString.split("\\s+")[2];
                    message = message.replaceAll(oldChar, newChar);
                    System.out.println(message);
                    break;
                case "Includes":
                    String isIncludes = inputString.split("\\s+")[1];
                    if (message.contains(isIncludes)) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                case "Start":
                    String starString = inputString.split("\\s+")[1];
                    if (message.startsWith(starString)) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                case "Lowercase":
                    message = message.toLowerCase();
                    System.out.println(message);
                    break;
                case "FindIndex":
                    String findElement = inputString.split("\\s+")[1];
                    int index = message.lastIndexOf(findElement);
                    System.out.println(index);
                    break;
                case "Remove":
                    int startIndexForRemove = Integer.parseInt(inputString.split("\\s+")[1]);
                    int countIndexForRemove = Integer.parseInt(inputString.split("\\s+")[2]);
                    String stringForRemove = message.substring
                            (startIndexForRemove, startIndexForRemove + countIndexForRemove);
                    message = message.replaceFirst(Pattern.quote(stringForRemove), "");
                    System.out.println(message);
                    break;
            }
            inputString = scanner.nextLine();
        }
    }
}
