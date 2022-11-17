package Exam_preparation_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TheLift_02_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberPeople = Integer.parseInt(scanner.nextLine());
        List<Integer> placeInLift = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        for (int index = 0; index <= placeInLift.size() - 1; index++) {
            int currentNumber = placeInLift.get(index);
            if (numberPeople >= 4 && currentNumber < 4) {
                int peoplesForAccommodation = 4 - currentNumber;
                placeInLift.set(index, peoplesForAccommodation + currentNumber);
                numberPeople -= peoplesForAccommodation;
                continue;
            }
            if (numberPeople > 0 && numberPeople < 4 && currentNumber <= numberPeople) {
                int peoplesForAccommodation = numberPeople - currentNumber;
                if (currentNumber + numberPeople > 4){
                    placeInLift.set(index, 4);
                }else {
                    placeInLift.set(index, peoplesForAccommodation + currentNumber);

                }
                numberPeople -= 4 - currentNumber;

            }
            if (numberPeople > 0 && currentNumber > numberPeople && currentNumber < 4) {
                int peoplesForAccommodation = currentNumber - numberPeople;
                placeInLift.set(index, peoplesForAccommodation + currentNumber);
                numberPeople -= peoplesForAccommodation;
                continue;
            }
            if (numberPeople <= 0) {
                System.out.println("The lift has empty spots!");
                for (int numbers : placeInLift) {
                    System.out.print(numbers + " ");
                }
                return;
            }
        }
        System.out.printf("There isn't enough space! %d people in a queue!\n", numberPeople);
        for (int numbers : placeInLift) {
            System.out.print(numbers + " ");
        }
    }
}
