package Exam_preparation_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Inventory_03_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> inventory = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("Craft!")) {
            String command = input.split(" - ")[0];
            switch (command) {
                case "Collect":
                    String item = input.split(" - ")[1];
                    if (!inventory.contains(item)) {
                        inventory.add(item);
                    }
                    break;
                case "Drop":
                    String dropItem = input.split(" - ")[1];
                    inventory.remove(dropItem);
                    break;
                case "Combine Items":
                    String commandsItems = input.split(" - ")[1];
                    String oldItem = commandsItems.split(":")[0];
                    String newItem = commandsItems.split(":")[1];
                    if (inventory.contains(oldItem)) {
                        int indexOldItem = 0;
                        for (int index = 0; index <= inventory.size() - 1 ; index++) {
                            String currentItem = inventory.get(index);
                            if (oldItem.equals(currentItem)) {
                                indexOldItem = index;
                            }
                        }
                        inventory.add(indexOldItem + 1, newItem);
                    }
                    break;
                case "Renew":
                    String renewItem = input.split(" - ")[1];
                    if (inventory.contains(renewItem)){
                        inventory.remove(renewItem);
                        inventory.add(renewItem);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        for (int index = 0; index <= inventory.size() - 1; index++) {
            String currentItem = inventory.get(index);
            if (index != inventory.size() - 1) {
                System.out.print(currentItem + ", ");
            } else {
                System.out.print(currentItem);
            }
        }
    }
}
