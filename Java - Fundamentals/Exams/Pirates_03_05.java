package Exam_preparation_02;

import java.util.*;

public class Pirates_03_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> mapPopulation = new LinkedHashMap<>();
        Map<String, Integer> mapGold = new LinkedHashMap<>();
        String inputCities = scanner.nextLine();
        while (!inputCities.equals("Sail")) {
            String city = inputCities.split("\\|\\|")[0];
            int population = Integer.parseInt(inputCities.split("\\|\\|")[1]);
            int gold = Integer.parseInt(inputCities.split("\\|\\|")[2]);
            if (!mapPopulation.containsKey(city)) {
                mapPopulation.put(city, population);
                mapGold.put(city, gold);
            } else {
                int newPopulation = mapPopulation.get(city) + population;
                int newGold = mapGold.get(city) + gold;
                mapPopulation.put(city, newPopulation);
                mapGold.put(city, newGold);
            }
            inputCities = scanner.nextLine();
        }
        String inputCommands = scanner.nextLine();
        while (!inputCommands.equals("End")) {
            String town = inputCommands.split("=>")[1];
            switch (inputCommands.split("=>")[0]) {
                case "Plunder":
                    int people = Integer.parseInt(inputCommands.split("=>")[2]);
                    int gold = Integer.parseInt(inputCommands.split("=>")[3]);
                    int newPopulationCity = mapPopulation.get(town) - people;
                    int newQuantityGoldInCity = mapGold.get(town) - gold;
                    System.out.printf("%s plundered! %d gold stolen, %d citizens killed.\n",
                            town, gold, people);
                    if (newPopulationCity <= 0 || newQuantityGoldInCity <= 0) {
                        System.out.printf("%s has been wiped off the map!\n", town);
                        mapPopulation.remove(town);
                        mapGold.remove(town);
                    } else {
                        mapPopulation.put(town, newPopulationCity);
                        mapGold.put(town, newQuantityGoldInCity);
                    }
                    break;
                case "Prosper":
                    int goldIncrease = Integer.parseInt(inputCommands.split("=>")[2]);
                    if (goldIncrease < 0) {
                        System.out.println("Gold added cannot be a negative number!");
                    } else {
                        int newQuantityGold = mapGold.get(town) + goldIncrease;
                        mapGold.put(town, newQuantityGold);
                        System.out.printf("%d gold added to the city treasury. %s now has %d gold.\n",
                                goldIncrease, town, newQuantityGold);
                    }
                    break;
            }
            inputCommands = scanner.nextLine();
        }

        if (mapPopulation.isEmpty()) {
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");
        } else {
            System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:\n",
                    mapGold.size());
            for (Map.Entry<String, Integer> entry : mapPopulation.entrySet()) {
                System.out.printf("%s -> Population: %d citizens, Gold: %d kg\n",
                        entry.getKey(), entry.getValue(), mapGold.get(entry.getKey()));
            }
        }
    }
}
