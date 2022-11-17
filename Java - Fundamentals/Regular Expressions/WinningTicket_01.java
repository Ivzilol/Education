package Traning_09;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WinningTicket_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> ticketsList = Arrays.stream(scanner.nextLine().split(" *, *")).collect(Collectors.toList());

        for (String currentTicket : ticketsList) {
            if (currentTicket.length() != 20) {
                System.out.println("invalid ticket");
                continue;
            }
            String leftTicket = currentTicket.substring(0, 10);
            String rightTicket = currentTicket.substring(10, 20);
            String leftMach = "a";
            String rightMatch = "s";
            Pattern pattern = Pattern.compile("[\\\\@]{6,}|[\\\\$]{6,}|[\\\\#]{6,}|[\\\\^]{6,}");
            Matcher leftSiteTicket = pattern.matcher(leftTicket);
            Matcher rightSiteTicket = pattern.matcher(rightTicket);
            if (leftSiteTicket.find()) {
                leftMach = leftSiteTicket.group();
            }
            if (rightSiteTicket.find()) {
                rightMatch = rightSiteTicket.group();
            }
            if (leftMach.substring(0, 1).equals(rightMatch.substring(0, 1))) {
                int matchCount = Math.min(leftMach.length(), rightMatch.length());
                if (matchCount == 10) {
                    System.out.printf("ticket \"%s\" - %d%s Jackpot!\n", currentTicket, matchCount, leftMach.charAt(0));
                } else {
                    System.out.printf("ticket \"%s\" - %d%s\n", currentTicket, matchCount, leftMach.charAt(0));
                }
            } else {
                System.out.printf("ticket \"%s\" - no match\n", currentTicket);
            }
        }
    }
}

