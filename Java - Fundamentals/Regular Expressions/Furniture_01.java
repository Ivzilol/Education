package Exercise_09;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Furniture_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("[>]{2}(?<name>[\\w]+)[<]{2}(?<price>[\\d.]+)!(?<quantity>[\\d]+)");
        List<String> purchases = new ArrayList<>();
        double totalMoney = 0;
        while (!input.equals("Purchase")) {
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String name = matcher.group("name");
                double price = Double.parseDouble(matcher.group("price"));
                double quantity = Double.parseDouble(matcher.group("quantity"));
                purchases.add(name);
                totalMoney += price * quantity;
            }
            input = scanner.nextLine();
        }
        System.out.println("Bought furniture:");
        purchases.forEach (kay -> System.out.printf("%s\n", kay));
        System.out.printf("Total money spend: %.2f", totalMoney);
    }
}
