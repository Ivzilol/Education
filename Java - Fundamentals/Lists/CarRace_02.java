package Traning_03;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CarRace_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> cars = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        double timeFirstCar = 0;
        double timeSecondCar = 0;


        for (int indexFirstCar = 0; indexFirstCar < cars.size() / 2; indexFirstCar++) {
            int currentNumber = cars.get(indexFirstCar);
            timeFirstCar += currentNumber;
            if (currentNumber == 0) {
                timeFirstCar = timeFirstCar * 0.8;
            }
        }
        for (int indexSecondCar = cars.size() - 1; indexSecondCar > cars.size() / 2; indexSecondCar--) {
            int currentNumber = cars.get(indexSecondCar);
            timeSecondCar += currentNumber;
            if (currentNumber == 0) {
                timeSecondCar = timeSecondCar * 0.8;
            }
        }
        if (timeFirstCar < timeSecondCar) {
            System.out.printf("The winner is left with total time: %.1f", timeFirstCar);
        } else {
            System.out.printf("The winner is right with total time: %.1f", timeSecondCar);
        }
    }
}
