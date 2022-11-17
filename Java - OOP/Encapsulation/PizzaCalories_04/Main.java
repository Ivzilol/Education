package Exercises_02.PizzaCalories_04;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String pizzaData = scanner.nextLine();
        String pizzaName = pizzaData.split("\\s+")[1];
        int toppingsNumber = Integer.parseInt(pizzaData.split("\\s+")[2]);
        Pizza pizza = new Pizza(pizzaName, toppingsNumber);

        String doughData = scanner.nextLine();
        String flourType = doughData.split("\\s+")[1];
        String beackingTechnique = doughData.split("\\s+")[2];
        double weight = Double.parseDouble(doughData.split("\\s+")[3]);
        Dough dough = new Dough(flourType, beackingTechnique, weight);

        pizza.setDough(dough);

        String toppingData = scanner.nextLine();

        while (toppingData.equals("END")){
            String toppingType = toppingData.split("\\s+")[1];
            double toppingWeight = Double.parseDouble(toppingData.split("\\s+")[2]);
            Topping topping = new Topping(toppingType, toppingWeight);
            pizza.addTopping(topping);
            toppingData = scanner.nextLine();
        }
        System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());
    }
}
