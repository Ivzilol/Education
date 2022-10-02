package Exercises_06.RawData_04;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        ArrayList<Car> carList = new ArrayList<>();

        for (int index = 0; index < n; index++) {
            String input = scanner.nextLine();
            String[] tokens = input.split("\\s+");
            String model = tokens[0];
            int speed = Integer.parseInt(tokens[1]);
            int power = Integer.parseInt(tokens[2]);
            int weight = Integer.parseInt(tokens[3]);
            String cargoType = tokens[4];
            ArrayList<Tire> tireList = new ArrayList<>();
            for (int i = 5; i < 12; i += 2) {
                int a = i + 1;
                Tire tire = new Tire(Double.parseDouble(tokens[i]), Integer.parseInt(tokens[a]));
                tireList.add(tire);
            }
            Engine engine = new Engine(speed, power);
            Cargo cargo = new Cargo(weight, cargoType);
            Car car = new Car(model, engine, cargo, tireList);
            carList.add(car);
        }
        String type = scanner.nextLine();
        if (type.equals("fragile")) {
            List<String> model = new ArrayList<>();
            for (int i = 0; i < carList.size(); i++) {
                if (carList.get(i).getTires().get(0).getTirePressure() < 1 ||
                        carList.get(i).getTires().get(0).getPressure2() < 1 ||
                        carList.get(i).getTires().get(0).getPressure3() < 1 ||
                        carList.get(i).getTires().get(0).getPressure4() < 1);
                model.add(carList.get(i).getModel());
            }
            model.forEach(e -> System.out.println(e));
        } else {
            carList.forEach(e -> {
                if (e.getCargo().getCargoType().equals("flamable") && e.getEngine().getEnginePower() > 250) {
                    System.out.printf("%s\n", e.getModel());
                }
            });
        }
    }
}
