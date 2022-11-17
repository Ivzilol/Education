package Exam_preparation_01;

import com.sun.source.tree.IfTree;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShoppingList_02_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> listProducts = Arrays.stream(scanner.nextLine().split("!"))
                .collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("Go Shopping!")) {
            String products = input.split(" ")[0];
            switch (products) {
                case "Urgent":
                    String urgent = input.split(" ")[1];
                    if (!listProducts.contains(urgent)) {
                        listProducts.add(0, urgent);
                    }
                    break;
                case "Unnecessary":
                    String unnecessary = input.split(" ")[1];
                    listProducts.remove(unnecessary);
                    break;
                case "Correct":
                    String correctOldItem = input.split(" ")[1];
                    String correctNewItem = input.split(" ")[2];
                    if (listProducts.contains(correctOldItem)) {
                        for (int index = 0; index <= listProducts.size() - 1; index++) {
                            String currentProducts = listProducts.get(index);
                            if (currentProducts.equals(correctOldItem)) {
                                listProducts.set(index, correctNewItem);
                            }
                        }
                    }
                    break;
                case "Rearrange":
                    String rearrange = input.split(" ")[1];
                    if (listProducts.contains(rearrange)) {
                        listProducts.remove(rearrange);
                        listProducts.add(rearrange);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        for (int index = 0; index <= listProducts.size() - 1; index++) {
            String currentProduct = listProducts.get(index);
            if (index != listProducts.size() - 1) {
                System.out.print(currentProduct + ", ");
            } else {
                System.out.print(currentProduct);
            }
        }
    }
}
