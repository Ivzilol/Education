package Exam_preparation_01;

import java.util.Scanner;

public class BonusScoringSystem_01_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double numberStudents = Double.parseDouble(scanner.nextLine());
        double numbersLectures = Double.parseDouble(scanner.nextLine());
        double bonus = Double.parseDouble(scanner.nextLine());

        double maxBonus = 0;
        double attendedLectures = 0;
        for (int i = 1; i <= numberStudents ; i++) {
            double attendances = Double.parseDouble(scanner.nextLine());
            double currentBonus =(attendances / numbersLectures) * (5 + bonus);
            if (currentBonus > maxBonus){
                maxBonus = currentBonus;
                attendedLectures = attendances;
            }
        }
        System.out.printf("Max Bonus: %.0f.\n", maxBonus);
        System.out.printf("The student has attended %.0f lectures.", attendedLectures);
    }
}
