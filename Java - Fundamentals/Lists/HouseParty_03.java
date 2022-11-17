package Exercise_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HouseParty_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberCommands = Integer.parseInt(scanner.nextLine());

        List<String> guestList = new ArrayList<>();

        for (int i = 1; i <= numberCommands; i++) {
            String input = scanner.nextLine();
            if (input.contains("is going!")) {
                String name = input.split(" ")[0];
                if (guestList.size() == 0 && input.contains("is going!")) {
                    guestList.add(name);
                    continue;
                }
                boolean isEquals = false;
                for (int index = 0; index < guestList.size(); index++) {
                    String nameInList = guestList.get(index);
                    if (name.equals(nameInList)) {
                        isEquals = true;
                        break;
                    }
                }
                if (!isEquals) {
                    guestList.add(name);
                } else {
                    System.out.printf("%s is already in the list!\n", name);
                }
            } else if (input.contains("is not going!")) {
                String name = input.split(" ")[0];
                boolean isEquals = false;
                for (int index = 0; index < guestList.size(); index++) {
                    String nameInList = guestList.get(index);
                    if (name.equals(nameInList)) {
                        isEquals = true;
                        guestList.remove(index);
                        break;
                    }
                }
                if (!isEquals) {
                    System.out.printf("%s is not in the list!\n", name);
                }
            }
        }
        for (String names : guestList) {
            System.out.println(names);
        }
    }
}
