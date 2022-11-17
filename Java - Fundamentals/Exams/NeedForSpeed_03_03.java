package Exam_preparation_02;

import java.util.*;

public class NeedForSpeed_03_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Integer>> carsMap = new LinkedHashMap<>();
        for (int index = 0; index < n; index++) {
            String input = scanner.nextLine();
            String car = input.split("\\|")[0];
            int kilometers = Integer.parseInt(input.split("\\|")[1]);
            int fuel = Integer.parseInt(input.split("\\|")[2]);
            List<Integer> kmAndFuel = new ArrayList<>();
            kmAndFuel.add(kilometers);
            kmAndFuel.add(fuel);
            carsMap.put(car, kmAndFuel);
        }
        String input = scanner.nextLine();
        while (!input.equals("Stop")) {
            String command = input.split(" : ")[0];
            switch (command) {
                case "Drive":
                    String car = input.split(" : ")[1];
                    int distance = Integer.parseInt(input.split(" : ")[2]);
                    int fuel = Integer.parseInt(input.split(" : ")[3]);
                    if (!carsMap.containsKey(car)) {
                        break;
                    }
                    List<Integer> kmAndFuel = carsMap.get(car);
                    int currentKm = 0;
                    int currentFuel = 0;
                    for (int index = 0; index < kmAndFuel.size(); index++) {
                        if (index == 0) {
                            currentKm = kmAndFuel.get(index) + distance;
                        } else {
                            currentFuel = kmAndFuel.get(index) - fuel;
                        }
                    }
                    if (currentFuel < 0) {
                        System.out.println("Not enough fuel to make that ride");
                        break;
                    }
                    List<Integer> newKmAndFuel = new ArrayList<>();
                    newKmAndFuel.add(currentKm);
                    newKmAndFuel.add(currentFuel);
                    carsMap.put(car, newKmAndFuel);
                    System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.\n", car, distance, fuel);
                    if (currentKm >= 100000) {
                        System.out.printf("Time to sell the %s!\n", car);
                        carsMap.remove(car);
                        break;
                    }
                    break;
                case "Refuel":
                    String carRefuel = input.split(" : ")[1];
                    int fuelForRefuel = Integer.parseInt(input.split(" : ")[2]);
                    if (!carsMap.containsKey(carRefuel)) {
                        break;
                    }
                    List<Integer> kmAndFuelForRefuel = carsMap.get(carRefuel);
                    int currentKmFuelCar = 0;
                    int currentFuelCar = 0;
                    int fuelAfterFuel = 0;
                    for (int index = 0; index < kmAndFuelForRefuel.size(); index++) {
                        if (index == 0) {
                            currentKmFuelCar = kmAndFuelForRefuel.get(index);
                        } else {
                            currentFuelCar = kmAndFuelForRefuel.get(index);
                            fuelAfterFuel = kmAndFuelForRefuel.get(index) + fuelForRefuel;
                        }
                    }
                    List<Integer> newList = new ArrayList<>();
                    if (fuelAfterFuel > 75) {
                        fuelAfterFuel = 75;
                        newList.add(currentKmFuelCar);
                        newList.add(fuelAfterFuel);
                        carsMap.put(carRefuel, newList);
                        System.out.printf("%s refueled with %d liters\n", carRefuel, 75 - currentFuelCar);
                    } else {
                        currentFuelCar += fuelForRefuel;
                        newList.add(currentKmFuelCar);
                        newList.add(currentFuelCar);
                        carsMap.put(carRefuel, newList);
                        System.out.printf("%s refueled with %d liters\n", carRefuel, fuelForRefuel);
                    }
                    break;
                case "Revert":
                    String carRevert = input.split(" : ")[1];
                    int kmRevert = Integer.parseInt(input.split(" : ")[2]);
                    if (!carsMap.containsKey(carRevert)) {
                        break;
                    }
                    List<Integer> listRevert = carsMap.get(carRevert);
                    int carKm = 0;
                    int carKmAfterRevert = 0;
                    int carFuel = 0;
                    for (int index = 0; index < listRevert.size(); index++) {
                        if (index == 0) {
                            carKm = listRevert.get(index);
                            carKmAfterRevert = listRevert.get(index) - kmRevert;
                        } else {
                            carFuel = listRevert.get(index);
                        }
                    }
                    List<Integer> newListRevert = new ArrayList<>();
                    if (carKmAfterRevert < 10000) {
                        carKmAfterRevert = 10000;
                        newListRevert.add(carKmAfterRevert);
                        newListRevert.add(carFuel);
                        carsMap.put(carRevert, newListRevert);
                    } else {
                        newListRevert.add(carKmAfterRevert);
                        newListRevert.add(carFuel);
                        carsMap.put(carRevert, newListRevert);
                        System.out.printf("%s mileage decreased by %d kilometers\n", carRevert, kmRevert);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        for (Map.Entry<String, List<Integer>> stringListEntry : carsMap.entrySet()) {
            System.out.printf("%s -> Mileage: %s kms, Fuel in the tank: %d lt.\n", stringListEntry.getKey(),
                    stringListEntry.getValue().get(0), stringListEntry.getValue().get(1));
        }
    }
}
