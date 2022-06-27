package Exercises_06;

import java.util.Scanner;

public class TrainTheTrainers_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberRating = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();
        double allRatings = 0;
        String presentation = "";
        double averageGrade = 0;
        double numberGrade = 0;
        while (!input.equals("Finish")) {
            presentation = input;
            averageGrade = 0;
            for (int j = 1; j <= numberRating; j++) {
                double ratings = Double.parseDouble(scanner.nextLine());
                averageGrade += ratings;
                allRatings += ratings;
                numberGrade++;
            }
            System.out.printf("%s - %.2f.\n", presentation, averageGrade / numberRating);
            input = scanner.nextLine();
        }
        System.out.printf("Student's final assessment is %.2f.", allRatings / numberGrade);
    }
}
