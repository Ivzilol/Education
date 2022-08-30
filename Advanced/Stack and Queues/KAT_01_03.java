package ExamPreparetion_01;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class KAT_01_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String licensePlatesInput = scanner.nextLine();
        String carsInput = scanner.nextLine();

        ArrayDeque<Integer> licensePlates = new ArrayDeque<>();
        ArrayDeque<Integer> cars = new ArrayDeque<>();

        Arrays.stream(licensePlatesInput.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(licensePlates::offer);
        Arrays.stream(carsInput.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(cars::push);
        int licenseCars = 0;
        int countDays = 0;
        while (!licensePlates.isEmpty() && !cars.isEmpty()) {
            int platesForDay = licensePlates.peek();
            int carForDay = cars.peek();
            countDays++;
            if (platesForDay == carForDay * 2) {
                cars.pop();
                licensePlates.poll();
                licenseCars += carForDay;
            } else if (platesForDay > (carForDay * 2)) {
                int plateRemain = platesForDay - (carForDay * 2);
                cars.pop();
                licensePlates.poll();
                licensePlates.offer(plateRemain);
                licenseCars += carForDay;
            } else {
                int remainsCar = carForDay - (platesForDay / 2);
                licensePlates.poll();
                cars.pop();
                cars.offer(remainsCar);
                int licenseCarForDay = carForDay - remainsCar;
                licenseCars += licenseCarForDay;
            }
        }
        System.out.printf("%d cars were registered for %d days!\n", licenseCars, countDays);
        if (licensePlates.isEmpty() && cars.isEmpty()) {
            System.out.println("Good job! There is no queue in front of the KAT!");
        } else if (!licensePlates.isEmpty()) {
            int sum = 0;
            for (Integer number : licensePlates) {
                sum += number;
            }
            System.out.printf("%d license plates remain!\n", sum);
        } else {
            int sum = 0;
            for (Integer number : cars) {
                sum += number;
            }
            System.out.printf("%d cars remain without license plates!\n", sum);
        }
    }
}
