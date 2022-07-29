package Exam_preparation_02;

import java.util.Scanner;
import java.util.regex.Pattern;

public class PasswordReset_01_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String massage = scanner.nextLine();
        String input = scanner.nextLine();

        while (!input.equals("Done")){
            String command = input.split(" ")[0];
            switch (command){
                case "TakeOdd":
                    StringBuilder newMassage = new StringBuilder();
                    for (int i = 0; i < massage.length(); i++) {
                        String currentChar = String.valueOf(massage.charAt(i));
                        if (i % 2 != 0){
                            newMassage.append(currentChar);
                        }
                    }
                    massage = String.valueOf(newMassage);
                    System.out.println(massage);
                    break;
                case "Cut":
                    int firstIndex = Integer.parseInt(input.split(" ")[1]);
                    int secondIndex = Integer.parseInt(input.split(" ")[2]);
                    String replaceText = massage.substring(firstIndex, firstIndex + secondIndex);
                    massage = massage.replaceFirst(Pattern.quote(replaceText), "");
                    System.out.println(massage);
                    break;
                case "Substitute":
                    String symbolForReplace = input.split(" ")[1];
                    String newSymbol = input.split(" ")[2];
                    if (massage.contains(symbolForReplace)){
                        massage = massage.replace(symbolForReplace, newSymbol);
                        System.out.println(massage);
                    }else {
                        System.out.println("Nothing to replace!");
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println("Your password is: " + massage);
    }
}
