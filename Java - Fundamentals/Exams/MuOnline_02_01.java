package Exam_preparation_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MuOnline_02_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> roomList = Arrays.stream(scanner.nextLine().split("\\|")).collect(Collectors.toList());

        int healthPoint = 100;
        int bitcoins = 0;
        int killedMonster = 0;
        int rooms = 0;

        for (int index = 0; index < roomList.size(); index++) {
            rooms++;
            String roomCommands = roomList.get(index);
            List<String> commands = List.of(roomCommands.split(" "));
            String firstCommand = commands.get(0);
            int secondCommand = Integer.parseInt(commands.get(1));
            if (firstCommand.equals("potion")) {
                healthPoint += secondCommand;
                if (healthPoint > 100) {
                    int currentHealPoints = healthPoint;
                    int newHealPoint = currentHealPoints - 100;
                    int healPointsHeal = secondCommand - newHealPoint;
                    healthPoint = 100;
                    System.out.printf("You healed for %d hp.\n", healPointsHeal);
                    System.out.printf("Current health: %d hp.\n", healthPoint);
                } else {
                    System.out.printf("You healed for %d hp.\n", secondCommand);
                    System.out.printf("Current health: %d hp.\n", healthPoint);
                }
            } else if (firstCommand.equals("chest")) {
                System.out.printf("You found %d bitcoins.\n", secondCommand);
                bitcoins += secondCommand;
            } else {
                healthPoint -= secondCommand;
                if (healthPoint > 0) {
                    System.out.printf("You slayed %s.\n", firstCommand);
                    killedMonster++;
                } else {
                    System.out.printf("You died! Killed by %s.\n", firstCommand);
                    System.out.printf("Best room: %d", rooms);
                    return;
                }
            }
        }
        System.out.println("You've made it!");
        System.out.printf("Bitcoins: %d\n", bitcoins);
        System.out.printf("Health: %d", healthPoint);
    }
}
