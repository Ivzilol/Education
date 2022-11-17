package Lab_04;

import java.util.Scanner;

public class Grades_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double grade = Double.parseDouble(scanner.nextLine());
        evaluation(grade);
    }
    public static void evaluation (double grade){
        if (grade <= 2.99){
            System.out.print("Fail");
        }else if (grade >= 3 && grade < 3.49){
            System.out.print("Poor");
        }else if (grade >= 3.50 && grade <= 4.49){
            System.out.print("Good");
        }else if (grade >= 4.50 && grade <= 5.49){
            System.out.print("Very good");
        }else if (grade >= 5.50){
            System.out.print("Excellent");
        }
    }
}
