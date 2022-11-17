package Exercise_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Train_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> carloads = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        int capacity = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();

        while (!input.equals("end")) {
            List<String> commands = Arrays.stream(input.split(" ")).collect(Collectors.toList());
            String command = commands.get(0);
            if (command.equals("Add")) {
                int numberPeopleInCarloads = Integer.parseInt(commands.get(1));
                carloads.add(numberPeopleInCarloads);
            } else {
                int numberPeople = Integer.parseInt(commands.get(0));
                for (int index = 0; index < carloads.size(); index++) {
                    int peopleInCarload = carloads.get(index);
                    int allPeople = numberPeople + peopleInCarload;
                    if (allPeople <= capacity) {
                        carloads.set(index, allPeople);
                        break;
                    }
                }
            }
            input = scanner.nextLine();
        }
        for (int numbers : carloads){
            System.out.print(numbers + " ");
        }
    }
}
