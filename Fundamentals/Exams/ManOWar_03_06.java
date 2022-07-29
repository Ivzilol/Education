package Exam_preparation_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManOWar_03_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> piratesShip = Arrays.stream(scanner.nextLine().split(">")).
                map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> warShip = Arrays.stream(scanner.nextLine().split(">")).
                map(Integer::parseInt).collect(Collectors.toList());
        int maxHealth = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();

        while (!input.equals("Retire")) {
            String command = input.split(" ")[0];
            switch (command) {
                case "Fire":
                    int indexAttack = Integer.parseInt(input.split(" ")[1]);
                    int damage = Integer.parseInt(input.split(" ")[2]);
                    if (indexAttack >= warShip.size() || indexAttack < 0) {
                        break;
                    }
                    warShip.set(indexAttack, warShip.get(indexAttack) - damage);
                    if (warShip.get(indexAttack) <= 0) {
                        System.out.print("You won! The enemy ship has sunken.");
                        return;
                    }
                    break;
                case "Defend":
                    int firstIndexAttack = Integer.parseInt(input.split(" ")[1]);
                    int secondIndexAttack = Integer.parseInt(input.split(" ")[2]);
                    int damagePiratesShip = Integer.parseInt(input.split(" ")[3]);
                    if (firstIndexAttack >= piratesShip.size() || secondIndexAttack >= piratesShip.size()
                            || firstIndexAttack < 0 || secondIndexAttack < 0) {
                        break;
                    }
                    for (int index = firstIndexAttack; index <= secondIndexAttack; index++) {
                        piratesShip.set(index, piratesShip.get(index) - damagePiratesShip);
                        if (piratesShip.get(index) <= 0) {
                            System.out.print("You lost! The pirate ship has sunken.");
                            return;
                        }
                    }
                    break;
                case "Repair":
                    int sectionRepair = Integer.parseInt(input.split(" ")[1]);
                    int sumRepair = Integer.parseInt(input.split(" ")[2]);
                    if (sectionRepair >= piratesShip.size() || sectionRepair < 0) {
                        break;
                    }
                    piratesShip.set(sectionRepair, piratesShip.get(sectionRepair) + sumRepair);
                    if (piratesShip.get(sectionRepair) > maxHealth) {
                        piratesShip.set(sectionRepair, maxHealth);
                    }
                    break;
                case "Status":
                    double under20percent = maxHealth * 0.20;
                    int countRepair = 0;
                    for (int index = 0; index <= piratesShip.size() - 1; index++) {
                        int currentNumber = piratesShip.get(index);
                        if (currentNumber < under20percent) {
                            countRepair++;
                        }
                    }
                    System.out.printf("%d sections need repair.\n", countRepair);
                    break;
            }
            input = scanner.nextLine();
        }
        int pirateShipStatus = 0;
        for (int index = 0; index <= piratesShip.size() - 1; index++) {
            int currentNumber = piratesShip.get(index);
            pirateShipStatus += currentNumber;

        }
        System.out.printf("Pirate ship status: %d\n", pirateShipStatus);
        int warShipStatus = 0;
        for (int index = 0; index <= warShip.size() - 1; index++) {
            int currentNumber = warShip.get(index);
            warShipStatus += currentNumber;
        }
        System.out.printf("Warship status: %d", warShipStatus);
    }
}
