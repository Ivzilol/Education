package Exam_preparation_02;

import java.util.*;

public class Pirates_03_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputCites = scanner.nextLine();
        Map<String, List<Integer>> mapCity = new LinkedHashMap<>();
        while (!inputCites.equals("Sail")) {
            String city = inputCites.split("\\|\\|")[0];
            int population = Integer.parseInt(inputCites.split("\\|\\|")[1]);
            int gold = Integer.parseInt(inputCites.split("\\|\\|")[2]);
            if (!mapCity.containsKey(city)) {
                List<Integer> populationAndCity = new ArrayList<>();
                populationAndCity.add(population);
                populationAndCity.add(gold);
                mapCity.put(city, populationAndCity);
            } else {
                List<Integer> newList = mapCity.get(city);
                int currentPopulation = newList.get(0) + population;
                int currentGold = newList.get(1) + gold;
                List<Integer> finalList = new ArrayList<>();
                finalList.add(currentPopulation);
                finalList.add(currentGold);
                mapCity.put(city, finalList);
            }
            inputCites = scanner.nextLine();
        }
        String input2 = scanner.nextLine();
        while (!input2.equals("End")) {
            String command = input2.split("=>")[0];
            String town = input2.split("=>")[1];
            if (!mapCity.containsKey(town)) {
                continue;
            }
            switch (command) {
                case "Plunder":
                    int peopleKilled = Integer.parseInt(input2.split("=>")[2]);
                    int goldSteal = Integer.parseInt(input2.split("=>")[3]);
                    List<Integer> peopleGold = mapCity.get(town);
                    int currentPeople = peopleGold.get(0) - peopleKilled;
                    int currentGold = peopleGold.get(1) - goldSteal;
                    List<Integer> newList = new ArrayList<>();
                    newList.add(currentPeople);
                    newList.add(currentGold);
                    mapCity.put(town, newList);
                    System.out.printf("%s plundered! %d gold stolen, %d citizens killed.\n", town, goldSteal, peopleKilled);
                    if (currentPeople <= 0 || currentGold <= 0) {
                        System.out.printf("%s has been wiped off the map!\n", town);
                        mapCity.remove(town);
                    }
                    break;
                case "Prosper":
                    int gold = Integer.parseInt(input2.split("=>")[2]);
                    if (gold < 0) {
                        System.out.println("Gold added cannot be a negative number!");
                        break;
                    } else {
                        List<Integer> goldProposer = mapCity.get(town);
                        int population = goldProposer.get(0);
                        int goldIncrease = goldProposer.get(1) + gold;
                        List<Integer> newList2 = new ArrayList<>();
                        newList2.add(population);
                        newList2.add(goldIncrease);
                        mapCity.put(town, newList2);
                        System.out.printf("%d gold added to the city treasury. %s now has %d gold.\n", gold, town, goldIncrease);
                    }
                    break;
            }
            input2 = scanner.nextLine();
        }
        if (mapCity.isEmpty()) {
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");
            return;
        }
        System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:\n", mapCity.size());
        for (Map.Entry<String, List<Integer>> entry : mapCity.entrySet()) {
            System.out.printf("%s -> Population: %d citizens, Gold: %d kg\n", entry.getKey(),
                    entry.getValue().get(0), entry.getValue().get(1));
        }
    }
}
