package Exercise_06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleCatalogue_06 {
    static class VehicleCatalogue {
        String type;
        String model;
        String color;
        int horsepower;

        public VehicleCatalogue(String type, String model, String color, int horsepower) {
            this.type = type;
            this.model = model;
            this.color = color;
            this.horsepower = horsepower;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getHorsepower() {
            return horsepower;
        }

        public void setHorsepower(int horsepower) {
            this.horsepower = horsepower;
        }

        @Override
        public String toString() {
            return String.format("Type: %s\nModel: %s\nColor: %s\nHorsepower: %d",
                    getType().toUpperCase().charAt(0) + this.getType().substring(1),
                    getModel(),
                    getColor(),
                    getHorsepower());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<VehicleCatalogue> vehicleCatalogueList = new ArrayList<>();
        while (!input.equals("End")) {
            String type = input.split("\\s+")[0];
            String model = input.split("\\s+")[1];
            String color = input.split("\\s+")[2];
            int horsePower = Integer.parseInt(input.split("\\s+")[3]);

            VehicleCatalogue vehicleCatalogue = new VehicleCatalogue(type, model, color, horsePower);
            vehicleCatalogueList.add(vehicleCatalogue);

            input = scanner.nextLine();
        }
        String model = scanner.nextLine();
        while (!model.equals("Close the Catalogue")) {
            for (VehicleCatalogue vehicleCatalogue : vehicleCatalogueList) {
                if (vehicleCatalogue.model.equals(model)) {
                    System.out.println(vehicleCatalogue.toString());
                }
            }
            model = scanner.nextLine();
        }
        double horsePowerCar = 0;
        double numberCars = 0;
        double horsePowerTruck = 0;
        double numberTruck = 0;
        for (VehicleCatalogue vehicleCatalogue : vehicleCatalogueList) {
            if (vehicleCatalogue.type.equals("car")) {
                horsePowerCar += vehicleCatalogue.getHorsepower();
                numberCars++;
            }
            if (vehicleCatalogue.type.equals("truck")) {
                horsePowerTruck += vehicleCatalogue.getHorsepower();
                numberTruck++;
            }
        }
        if (numberCars == 0) {
            System.out.printf("Cars have average horsepower of: %.2f.\n", zeroVehicle(numberCars));
        } else {
            System.out.printf("Cars have average horsepower of: %.2f.\n", horsePowerCar / numberCars);
        }
        if (numberTruck == 0) {
            System.out.printf("Trucks have average horsepower of: %.2f.\n", zeroTruck(numberTruck));
        } else {
            System.out.printf("Trucks have average horsepower of: %.2f.\n", horsePowerTruck / numberTruck);
        }
    }

    private static double zeroVehicle(double numberCars) {
        if (numberCars == 0) {
            return 0.00;
        }

        return 0.00;
    }

    private static double zeroTruck(double numberTruck) {
        if (numberTruck == 0) {
            return 0.00;
        }
        return 0.00;
    }
}
