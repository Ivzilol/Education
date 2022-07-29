package Exam_preparation_01;

import java.util.Scanner;

public class GuineaPig_01_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double kgFood = Double.parseDouble(scanner.nextLine());
        double kgHay  = Double.parseDouble(scanner.nextLine());
        double kgCover  = Double.parseDouble(scanner.nextLine());
        double petsKg = Double.parseDouble(scanner.nextLine());
        double footInGm = kgFood * 1000;
        double hayInGm = kgHay * 1000;
        double coverInGm = kgCover * 1000;
        double petsInGm = petsKg * 1000;

        double days = 30;
        while (footInGm >= 0 && hayInGm >= 0 && coverInGm >= 0 && days > 0){
            footInGm -= 300;
            days -= 1;
            if (days % 2 == 0){
                hayInGm -= footInGm * 0.05;
            }
            if (days % 3 == 0){
                coverInGm -= petsInGm / 3;
            }
        }
        double foodLeft = footInGm / 1000;
        double hayLeft = hayInGm / 1000;
        double coverLeft = coverInGm / 1000;
        if (footInGm > 0 && hayInGm >= 0 && coverInGm >= 0){
            System.out.printf("Everything is fine! Puppy is happy! Food: %.2f, Hay: %.2f, Cover: %.2f.", foodLeft, hayLeft, coverLeft);

        }else {
            System.out.print("Merry must go to the pet store!");
        }
    }
}
