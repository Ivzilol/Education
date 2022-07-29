package Lab_09;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchPhoneNumber_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("\\+359([ -])2\\1\\d{3}\\1\\d{4}\\b");
        Matcher matcher = pattern.matcher(input);
        List<String> listNumber = new ArrayList<>();
        while (matcher.find()) {
            String phoneNumber = matcher.group();
            listNumber.add(phoneNumber);
        }
        for (int index = 0; index <= listNumber.size() - 1; index++) {
            if (index != listNumber.size() - 1) {
                System.out.print(listNumber.get(index) + ", ");
            } else {
                System.out.print(listNumber.get(index));
            }
        }
    }
}
