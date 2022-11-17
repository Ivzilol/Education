package Exercises_04;

import java.util.Scanner;

public class Oscars_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        double points = Double.parseDouble(scanner.nextLine());
        int numberOfEvaluators = Integer.parseInt(scanner.nextLine());

        double pointsName = 0;

        for (int i = 1; i <= numberOfEvaluators; i++) {
            String nameEvaluators = scanner.nextLine();
            double pointsEvaluators = Double.parseDouble(scanner.nextLine());

            pointsName = points += (nameEvaluators.length() * pointsEvaluators) / 2;

            if (pointsName > 1250.5) {
                System.out.printf("Congratulations, %s got a nominee for leading role with %.1f!", name, pointsName);
                break;
            }
        }
        if (pointsName < 1250.5) {
            System.out.printf("Sorry, %s you need %.1f more!", name, (1250.5 - pointsName));
        }

    }
}
