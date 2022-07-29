package Exam_preparation_01;

import java.util.Scanner;

public class SoftUniReception_01_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double firstEmployeeEfficiencyPerHour = Double.parseDouble(scanner.nextLine());
        double secondEmployeeEfficiencyPerHour = Double.parseDouble(scanner.nextLine());
        double thirdEmployeeEfficiencyPerHour = Double.parseDouble(scanner.nextLine());
        double numberStudents = Double.parseDouble(scanner.nextLine());
        double allEfficiencyPerHour = firstEmployeeEfficiencyPerHour + secondEmployeeEfficiencyPerHour + thirdEmployeeEfficiencyPerHour;
        double countHours = 0;
        double countRest = 0;
        while (numberStudents > 0) {
            countHours++;
            numberStudents -= allEfficiencyPerHour;
            if (numberStudents == 0) {
                break;
            }
            if (countHours % 3 == 0) {
                countRest++;
            }
        }
        System.out.printf("Time needed: %.0fh.", countHours + countRest);
    }
}
