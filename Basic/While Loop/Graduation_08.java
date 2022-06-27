package Lab_5;

import java.util.Scanner;

public class Graduation_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        int clas = 1;
        double sum = 0;
        double highGrade = 0;
        double lowGrade = 0;
        double allGrade = 0;

        while (clas <= 12) {
            double estimates = Double.parseDouble(scanner.nextLine());
            clas++;
            sum += estimates;
            if (estimates >= 4) {
                highGrade++;
            }
            if (estimates < 4) {
                lowGrade++;
            }
            if (lowGrade == 2) {
                System.out.printf("%s has been excluded at %.0f grade", name, allGrade);
                break;
            }
            allGrade++;
        }
        if (highGrade > 11) {
            System.out.printf("%s graduated. Average grade: %.2f", name, sum / allGrade);
        }
    }
}
