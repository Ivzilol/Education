package Exercises_05;

import com.sun.source.tree.IfTree;

import java.util.Scanner;

public class ExamPreparation_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countPoorGrates = Integer.parseInt(scanner.nextLine());
        int count = 0;
        int totalSum = 0;
        double countProblem = 0;
        String lastProblem = "";
        boolean win = false;
        while (count < countPoorGrates){
            String problemName = scanner.nextLine();
            if (problemName.equals("Enough")){
                win = true;
                break;
            }
            int currentGrade = Integer.parseInt(scanner.nextLine());
            if (currentGrade <= 4){
                count++;
            }
            countProblem++;
            totalSum += currentGrade;
            lastProblem = problemName;

        }
        if (win){
            System.out.printf("Average score: %.2f%n", totalSum/countProblem);
            System.out.printf("Number of problems: %.0f%n", countProblem);
            System.out.printf("Last problem: %s", lastProblem);
        }else {
            System.out.printf("You need a break, %d poor grades.", countPoorGrates);
        }


    }
}
