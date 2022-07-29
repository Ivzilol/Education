package Lab_05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListOfProducts_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<String> productsList = new ArrayList<>();
        for (int index = 0; index < n; index++) {
            String products = scanner.nextLine();
            productsList.add(products);
        }
        Collections.sort(productsList);
        int count = 0;
        for (String items : productsList) {
            count++;
            System.out.printf("%d.", count);
            System.out.println(items);
        }

    }
}
