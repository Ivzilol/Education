package Exam_preparation_02;

import java.util.Scanner;
import java.util.regex.Pattern;

public class SecretChat_01_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String massage = scanner.nextLine();

        String input = scanner.nextLine();
        while (!input.equals("Reveal")) {
            String commands = input.split(":\\|:")[0];
            switch (commands) {
                case "InsertSpace":
                    int indexInsert = Integer.parseInt(input.split(":\\|:")[1]);
                    String symbolInsert = " ";
                    String firstIndex = massage.substring(0, indexInsert);
                    String secondSymbol = massage.substring(indexInsert);
                    massage = firstIndex.concat(symbolInsert).concat(secondSymbol);
                    System.out.println(massage);
                    break;
                case "Reverse":
                    String text = input.split(":\\|:")[1];
                    if (massage.contains(text)) {
                        String forReplace = new StringBuilder(text).reverse().toString();
                        massage = massage.replaceFirst(Pattern.quote(text), "") + forReplace;
                        System.out.println(massage);
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "ChangeAll":
                    String changedSymbol = input.split(":\\|:")[1];
                    String newSymbol = input.split(":\\|:")[2];
                    massage = massage.replace(changedSymbol, newSymbol);
                    System.out.println(massage);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.printf("You have a new text message: %s", massage);
    }
}
