package Exercises_05;

import java.util.Scanner;

public class OldBooks_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String wantedBook = scanner.nextLine();
        boolean isFound = false;
        int counter = 0;
        String input = scanner.nextLine();
        while (!input.equals("No More Books")){
            if (input.equals(wantedBook)){
                isFound = true;
                break;

            }
            counter++;
            input = scanner.nextLine();

        }
        if (isFound){
            System.out.printf("You checked %d books and found it.", counter);
        }else {
            System.out.printf("The book you search is not here!%n");
            System.out.printf("You checked %d books.", counter);
        }


    }
}
