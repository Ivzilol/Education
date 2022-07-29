package Exam_preparation_02;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes_02_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int index = 0; index < n; index++) {
            String input = scanner.nextLine();
            String regex = "@#+(?<barcode>[A-Z][A-Za-z0-9]{4,}[A-Z])@#+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            boolean isFind = true;
            while (matcher.find()) {
                isFind = false;
                String barcode = matcher.group("barcode");
                StringBuilder numbers = new StringBuilder();
                for (int indexBarcode = 0; indexBarcode < barcode.length(); indexBarcode++) {
                    char currentSymbol = barcode.charAt(indexBarcode);
                    if (Character.isDigit(currentSymbol)) {
                        numbers.append(currentSymbol);
                    }
                }
                if (numbers.length() == 0) {
                    System.out.println("Product group: 00");
                } else {
                    System.out.println("Product group: " + numbers);
                }
            }
            if (isFind) {
                System.out.println("Invalid barcode");
            }
        }
    }
}
