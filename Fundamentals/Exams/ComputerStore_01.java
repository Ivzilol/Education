package Exam_preparation_01;

import java.util.Scanner;

public class ComputerStore_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        double finalPriceWithoutTaxes = 0;
        while (!input.equals("special") && !input.equals("regular")){
            double partsPrice = Double.parseDouble(input);
            if (partsPrice < 0){
                System.out.println("Invalid price!");
                input = scanner.nextLine();
                continue;
            }
            finalPriceWithoutTaxes += partsPrice;
            input = scanner.nextLine();
        }
        if (finalPriceWithoutTaxes <= 0){
            System.out.println("Invalid order!");
            return;
        }
        if (input.equals("regular")){
            double taxes = finalPriceWithoutTaxes * 0.20;
            System.out.println("Congratulations you've just bought a new computer!");
            System.out.printf("Price without taxes: %.2f$\n", finalPriceWithoutTaxes);
            System.out.printf("Taxes: %.2f$\n", taxes);
            System.out.println("-----------");
            System.out.printf("Total price: %.2f$", finalPriceWithoutTaxes + taxes);
        }
        if (input.equals("special")){
            double taxes = finalPriceWithoutTaxes * 0.20;
            double charge = (finalPriceWithoutTaxes + taxes) * 0.10;
            System.out.println("Congratulations you've just bought a new computer!");
            System.out.printf("Price without taxes: %.2f$\n", finalPriceWithoutTaxes);
            System.out.printf("Taxes: %.2f$\n", taxes);
            System.out.println("-----------");
            System.out.printf("Total price: %.2f$", (finalPriceWithoutTaxes + taxes) - charge);
        }
    }
}
