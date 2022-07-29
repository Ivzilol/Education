package Exam_preparation_02;

import java.util.Scanner;

public class TheImitationGame_01_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String massage = scanner.nextLine();

        String input = scanner.nextLine();

        while (!input.equals("Decode")) {
            String command = input.split("\\|")[0];
            switch (command) {
                case "Move":
                    int numberLetters = Integer.parseInt(input.split("\\|")[1]);
                    String firstElement = massage.substring(0, numberLetters);
                    String lastElement = massage.substring(numberLetters);
                    massage = lastElement.concat(firstElement);
                    break;
                case "Insert":
                    int indexInsert = Integer.parseInt(input.split("\\|")[1]);
                    String symbolInsert = input.split("\\|")[2];
                    String firstIndex = massage.substring(0, indexInsert);
                    String insertSymbol = massage.substring(indexInsert);
                    massage = firstIndex.concat(symbolInsert).concat(insertSymbol);

                    break;
                case "ChangeAll":
                    String oldSymbol = input.split("\\|")[1];
                    String changedSymbol = input.split("\\|")[2];
                    massage = massage.replace(oldSymbol, changedSymbol);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.printf("The decrypted message is: %s", massage);
    }
}
