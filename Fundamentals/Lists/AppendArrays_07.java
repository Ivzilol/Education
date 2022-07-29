package Exercise_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AppendArrays_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> stringList = Arrays.stream(scanner.nextLine().split("\\|")).collect(Collectors.toList());

        List<String> newList = new ArrayList<>();

        for (int i = stringList.size() - 1; i >= 0; i--) {
            String[] numbers = stringList.get(i).split("\\s+");
            for (String number : numbers) {
                if (!number.equals("")) {
                    newList.add(number);
                }
            }
        }
        String print = newList.toString().trim();
        System.out.println(print.replaceAll("\\[|,|\\]", ""));
    }
}
