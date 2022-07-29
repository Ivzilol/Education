package Exercise_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrayManipulator_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> mainArray = Arrays.stream(scanner.nextLine().split("\\s+")).
                map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String command = input.split("\\s+")[0];
            switch (command) {
                case "exchange":
                    int commandsExchange = Integer.parseInt(input.split(" ")[1]);
                    int count = 0;
                    if (commandsExchange < 0 || commandsExchange >= mainArray.size()) {
                        System.out.println("Invalid index");
                        break;
                    }
                    for (int index = 0; index <= commandsExchange; index++) {
                        int currentNumber = mainArray.get(index);
                        mainArray.add(currentNumber);
                        mainArray.remove(0);
                        index -= 1;
                        if (count == commandsExchange) {
                            break;
                        }
                        count++;
                    }
                    break;
                case "max":
                    String evenOrOdd = input.split(" ")[1];
                    if (evenOrOdd.equals("even")) {
                        int maxEvenIndex = 0;
                        int maxEvenNumber = Integer.MIN_VALUE;
                        boolean isEven = false;
                        for (int index = 0; index <= mainArray.size() - 1; index++) {
                            int currentNumber = mainArray.get(index);
                            if (currentNumber % 2 == 0) {
                                isEven = true;
                                if (maxEvenNumber <= currentNumber) {
                                    maxEvenIndex = index;
                                    maxEvenNumber = currentNumber;
                                }
                            }
                        }
                        if (!isEven) {
                            System.out.println("No matches");
                        } else {
                            System.out.printf("%d\n", maxEvenIndex);
                        }
                    }
                    if (evenOrOdd.equals("odd")) {
                        int maxOddIndex = 0;
                        int maxOddNumber = Integer.MIN_VALUE;
                        boolean isOdd = false;
                        for (int index = 0; index <= mainArray.size() - 1; index++) {
                            int currentNumber = mainArray.get(index);
                            if (currentNumber % 2 != 0) {
                                isOdd = true;
                                if (maxOddNumber <= currentNumber) {
                                    maxOddIndex = index;
                                    maxOddNumber = currentNumber;
                                }
                            }
                        }
                        if (!isOdd) {
                            System.out.println("No matches");
                        } else {
                            System.out.printf("%d\n", maxOddIndex);
                        }
                    }
                    break;
                case "min":
                    String evenOrOddMin = input.split(" ")[1];
                    if (evenOrOddMin.equals("even")) {
                        int minEvenIndex = 0;
                        int minEvenNumber = Integer.MAX_VALUE;
                        boolean isEven = false;
                        for (int index = 0; index <= mainArray.size() - 1; index++) {
                            int currentNumber = mainArray.get(index);
                            if (currentNumber % 2 == 0) {
                                isEven = true;
                                if (currentNumber <= minEvenNumber) {
                                    minEvenIndex = index;
                                    minEvenNumber = currentNumber;
                                }
                            }
                        }
                        if (!isEven) {
                            System.out.println("No matches");
                        } else {
                            System.out.printf("%d\n", minEvenIndex);
                        }
                    }
                    if (evenOrOddMin.equals("odd")) {
                        int minOddIndex = 0;
                        int minOddNumber = Integer.MAX_VALUE;
                        boolean isOdd = false;
                        for (int index = 0; index <= mainArray.size() - 1; index++) {
                            int currentNumber = mainArray.get(index);
                            if (currentNumber % 2 != 0) {
                                isOdd = true;
                                if (currentNumber <= minOddNumber) {
                                    minOddIndex = index;
                                    minOddNumber = currentNumber;
                                }
                            }
                        }
                        if (!isOdd) {
                            System.out.println("No matches");
                        } else {
                            System.out.printf("%d\n", minOddIndex);
                        }
                    }
                    break;
                case "first":
                    int numberEven = Integer.parseInt(input.split(" ")[1]);
                    String commandEvenOdd = input.split(" ")[2];
                    if (numberEven > mainArray.size() || numberEven < 0) {
                        System.out.println("Invalid count");
                        break;
                    }
                    if (commandEvenOdd.equals("even")) {
                        int countEven = 0;
                        List<Integer> arrayFirstEven = new ArrayList<>();
                        for (int index = 0; index <= mainArray.size() - 1; index++) {
                            int currentNumber = mainArray.get(index);
                            if (currentNumber % 2 == 0) {
                                arrayFirstEven.add(currentNumber);
                                countEven++;
                            }
                            if (countEven == numberEven) {
                                break;
                            }
                        }
                        System.out.println(arrayFirstEven);
                    }
                    if (commandEvenOdd.equals("odd")) {
                        int countOdd = 0;
                        List<Integer> arrayFirstOdd = new ArrayList<>();
                        for (int index = 0; index <= mainArray.size() - 1; index++) {
                            int currentNumber = mainArray.get(index);
                            if (currentNumber % 2 != 0) {
                                arrayFirstOdd.add(currentNumber);
                                countOdd++;
                            }
                            if (countOdd == numberEven) {
                                break;
                            }
                        }
                        System.out.println(arrayFirstOdd);
                    }
                    break;
                case "last":
                    int numberEvenOrOdd = Integer.parseInt(input.split(" ")[1]);
                    String commandIvenOrOdd = input.split(" ")[2];
                    if (numberEvenOrOdd > mainArray.size() || numberEvenOrOdd < 0) {
                        System.out.println("Invalid count");
                        break;
                    }
                    if (commandIvenOrOdd.equals("even")) {
                        int countEven = 0;
                        List<Integer> arrayLastEven = new ArrayList<>();
                        for (int index = mainArray.size() - 1; index >= 0; index--) {
                            int currentNumber = mainArray.get(index);
                            if (currentNumber % 2 == 0) {
                                arrayLastEven.add(0, currentNumber);
                                countEven++;
                            }
                            if (countEven == numberEvenOrOdd) {
                                break;
                            }
                        }
                        System.out.println(arrayLastEven);
                    }
                    if (commandIvenOrOdd.equals("odd")) {
                        int countOdd = 0;
                        List<Integer> arrayLastOdd = new ArrayList<>();
                        for (int index = mainArray.size() - 1; index >= 0; index--) {
                            int currentNumber = mainArray.get(index);
                            if (currentNumber % 2 != 0) {
                                arrayLastOdd.add(0, currentNumber);
                                countOdd++;
                            }
                            if (countOdd == numberEvenOrOdd) {
                                break;
                            }
                        }
                        System.out.println(arrayLastOdd);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println(mainArray);
    }
}
