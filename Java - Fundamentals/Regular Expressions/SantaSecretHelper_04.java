package Traning_09;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SantaSecretHelper_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int key = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();
        while (!input.equals("end")) {
            String currentMassage = input;
            StringBuilder decryptedMassage = new StringBuilder();
            for (int index = 0; index < currentMassage.length(); index++) {
                char currentSymbol = currentMassage.charAt(index);
                char decryptedSymbol = (char) (currentSymbol - key);
                decryptedMassage.append(decryptedSymbol);
            }
            String regexName = "@([A-Za-z]+)[^@\\-!:>]*!([G])!";
            Pattern patternName = Pattern.compile(regexName);
            Matcher matcherName = patternName.matcher(decryptedMassage);
            StringBuilder name = new StringBuilder();
            if (matcherName.find()) {
                name.append(matcherName.group(1));
            }
            String regexType = "@([A-Za-z]+)[^@\\-!:>]*!([G])!";
            Pattern patternType = Pattern.compile(regexType);
            Matcher matcherType = patternType.matcher(decryptedMassage);
            StringBuilder type = new StringBuilder();
            if (matcherType.find()) {
                type.append(matcherType.group(2));
            }
            String finalName = name.substring(0, name.length());
            String finalType = type.substring(0);
            if (finalType.equals("G")) {
                System.out.println(finalName);
            }
            input = scanner.nextLine();
        }
    }
}
