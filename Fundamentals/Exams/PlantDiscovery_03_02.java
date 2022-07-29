package Exam_preparation_02;

import java.util.*;

public class PlantDiscovery_03_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberPlants = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> plantsMap = new HashMap<>();
        Map<String, List<Double>> ratings = new HashMap<>();
        for (int index = 0; index < numberPlants; index++) {
            String input = scanner.nextLine();
            String plant = input.split("<->")[0];
            int rarity = Integer.parseInt(input.split("<->")[1]);
            plantsMap.compute(plant, (k, v) -> rarity);
            ratings.putIfAbsent(plant, new ArrayList<>());
        }
        String input = scanner.nextLine();
        while (!input.equals("Exhibition")){
            String[] tokens = input.split(": ");
            String command = tokens[0];
            String[] elements = tokens[1].split(" - ");
            switch (command){
                case "Rate":
                    String plant = elements[0];
                    double rating = Double.parseDouble(elements[1]);
                    if (!plantsMap.containsKey(plant)){
                        System.out.println("error");
                        break;
                    }else {
                        ratings.get(plant).add(rating);
                    }
                    break;
                case "Update":
                    String updatePlant = elements[0];
                    int newRarity = Integer.parseInt(elements[1]);
                    if (!plantsMap.containsKey(updatePlant)){
                        System.out.println("error");
                        break;
                    }else {
                        plantsMap.compute(updatePlant, (k, v) -> newRarity);
                    }
                    break;
                case "Reset":
                    String name = elements[0];
                    if (!plantsMap.containsKey(name)){
                        System.out.println("error");
                    }else {
                        ratings.get(name).clear();
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println("Plants for the exhibition:");
        plantsMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .thenComparingDouble(x -> ratings.get(x.getKey()).stream()
                                .mapToDouble(Double::doubleValue)
                                .average().orElse(0.0)).reversed())
                .forEach(e -> System.out.printf("- %s; Rarity: %d; Rating: %.2f\n", e.getKey(), e.getValue(),
                        ratings.get(e.getKey()).stream().mapToDouble(Double::doubleValue).average().orElse(0.0)));
    }
}
