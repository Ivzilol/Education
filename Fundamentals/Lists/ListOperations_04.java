package Exercise_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListOperations_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbersList = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();
        while (!input.equals("End")) {
            List<String> commands = Arrays.stream(input.split(" ")).collect(Collectors.toList());
            String command = commands.get(0);
            switch (command) {
                case "Add" -> {
                    int addNumber = Integer.parseInt(commands.get(1));
                    numbersList.add(addNumber);
                }
                case "Insert" -> {
                    int insertNumber = Integer.parseInt(commands.get(1));
                    int indexNumber = Integer.parseInt(commands.get(2));
                    if (isIndexIsValid(indexNumber, numbersList)) {
                        numbersList.add(indexNumber, insertNumber);
                    } else {
                        System.out.println("Invalid index");
                    }
                }
                case "Remove" -> {
                    int removeIndex = Integer.parseInt(commands.get(1));
                    if (isIndexIsValid(removeIndex, numbersList)) {
                        numbersList.remove(removeIndex);
                    } else {
                        System.out.println("Invalid index");
                    }
                }
                case "Shift" -> {
                    String shiftLeftOrRight = commands.get(1);
                    if (shiftLeftOrRight.equals("left")) {
                        int shiftLEft = Integer.parseInt(commands.get(2));
                        for (int i = 1; i <= shiftLEft; i++) {
                            int firstNumber = numbersList.get(0);
                            numbersList.add(firstNumber);
                            numbersList.remove(0);
                        }
                    } else if (shiftLeftOrRight.equals("right")) {
                        int shiftRight = Integer.parseInt(commands.get(2));
                        for (int i = 1; i <= shiftRight; i++) {
                            int lastNumber = numbersList.get(numbersList.size() - 1);
                            numbersList.add(0, lastNumber);
                            numbersList.remove(numbersList.size() - 1);
                        }
                    }
                }
            }
            input = scanner.nextLine();
        }
        System.out.println(numbersList.toString().replaceAll("[\\[\\],]", ""));
    }

    public static boolean isIndexIsValid(int indexNumber, List<Integer> numbersList) {
        return indexNumber >= 0 && indexNumber <= numbersList.size() - 1;
    }
}
