package Exercises_06.SpeedRacing_03;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Car> carMap = new LinkedHashMap<>();

        for (int index = 0; index < n; index++) {
            String[] input = scanner.nextLine().split("\\s+");
            String modelCar = input[0];
            double fuelAmount = Double.parseDouble(input[1]);
            double fuelCostForOneKm = Double.parseDouble(input[2]);
            double distanceTraveled = 0;
            Car car = new Car(modelCar, fuelAmount, fuelCostForOneKm, distanceTraveled);
            carMap.put(car.getModel(), car);
        }
        String command = scanner.nextLine();
        while (!command.equals("End")) {
            String[] driveCommands = command.split("\\s+");
            String modelCar = driveCommands[1];
            double amountOfKm = Double.parseDouble(driveCommands[2]);
            double amountFuelInCar = carMap.get(modelCar).getFuelAmount();
            double fuelOnKm = carMap.get(modelCar).getFuelCostForKilometer();
            double spentFuel = amountOfKm * fuelOnKm;
            if (amountFuelInCar >= spentFuel) {
                double newAmountFuel = amountFuelInCar - spentFuel;
                carMap.get(modelCar).setFuelAmount(newAmountFuel);
                double currentKm = carMap.get(modelCar).getDistanceTraveled();
                carMap.get(modelCar).setDistanceTraveled(currentKm + amountOfKm);
            } else {
                System.out.println("Insufficient fuel for the drive");
            }
            command = scanner.nextLine();
        }
        for (Car car : carMap.values()) {
            System.out.println(car.toString());
        }

    }
}
