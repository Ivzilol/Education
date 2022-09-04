package Exercises_03;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class UniqueUsernames_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Set<String> usernames = new LinkedHashSet<>();
        for (int index = 0; index < n; index++) {
            String username = scanner.nextLine();
            usernames.add(username);
        }
        for (String currentUsername : usernames) {
            System.out.println(currentUsername);
        }
    }
}
