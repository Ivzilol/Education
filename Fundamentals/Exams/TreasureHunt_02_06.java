package Exam_preparation_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TreasureHunt_02_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> treasureChest = Arrays.stream(scanner.nextLine().split("\\|")).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("Yohoho!")) {
            String command = input.split(" ")[0];
            List<String> lootList = new ArrayList<>();
            switch (command) {
                case "Loot":
                    lootList = List.of(input.split(" "));
                    for (int index = 1; index <= lootList.size() - 1; index++) {
                        String currentItemLoot = lootList.get(index);
                        if (!treasureChest.contains(currentItemLoot)) {
                            treasureChest.add(0, currentItemLoot);
                        }
                    }
                    break;
                case "Drop":
                    int dropPosition = Integer.parseInt(input.split(" ")[1]);
                    if (dropPosition >= 0 && dropPosition <= treasureChest.size() - 1) {
                        for (int index = 0; index <= treasureChest.size() - 1; index++) {
                            String currentItem = treasureChest.get(index);
                            if (index == dropPosition) {
                                treasureChest.remove(currentItem);
                                treasureChest.add(currentItem);
                            }
                        }
                    }
                    break;
                case "Steal":
                    int stealNumber = Integer.parseInt(input.split(" ")[1]);
                    List<String> stealList = new ArrayList<>();
                    if (stealNumber > treasureChest.size()) {
                        stealNumber = treasureChest.size();
                    }
                    for (int index = treasureChest.size() - 1; index >= treasureChest.size() - stealNumber; index--) {
                        String currentItem = treasureChest.get(index);
                        stealList.add(currentItem);
                    }
                    for (int i = 1; i <= stealNumber; i++) {
                        treasureChest.remove(treasureChest.size() - 1);
                    }
                    for (int index = stealList.size() - 1; index >= 0; index--) {
                        String currentElement = stealList.get(index);
                        if (index != 0) {
                            System.out.print(currentElement + ", ");
                        } else {
                            System.out.println(currentElement);
                        }
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        if (treasureChest.isEmpty()) {
            System.out.print("Failed treasure hunt.");
        } else {
            double numberItems = 0;
            double sumElements = 0;
            for (int index = 0; index <= treasureChest.size() - 1; index++) {
                numberItems++;
                String currentElement = treasureChest.get(index);
                for (int indexElement = 0; indexElement <= currentElement.length() - 1; indexElement++) {
                    sumElements++;
                }
            }
            System.out.printf("Average treasure gain: %.2f pirate credits.", sumElements / numberItems);
        }
    }
}
