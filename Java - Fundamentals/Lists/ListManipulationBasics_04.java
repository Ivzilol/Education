package Lab_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListManipulationBasics_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numberList = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("end")) {
            List<String> commands = Arrays.stream(input.split(" ")).collect(Collectors.toList());
            String command = commands.get(0);
            switch (command) {
                case "Add":
                    int numberAd = Integer.parseInt(commands.get(1));
                    numberList.add(numberAd);
                    break;
                case "Remove":
                    int numberRemove = Integer.parseInt(commands.get(1));
                    numberList.remove(Integer.valueOf(numberRemove));
                    break;
                case "RemoveAt":
                    int indexRemove = Integer.parseInt(commands.get(1));
                    numberList.remove(indexRemove);
                    break;
                case "Insert":
                    int numberToInsert = Integer.parseInt(commands.get(1));
                    int indexToInsert = Integer.parseInt(commands.get(2));
                    numberList.add(indexToInsert, numberToInsert);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println(numberList.toString().replaceAll("[\\[\\],]", ""));
    }
}
