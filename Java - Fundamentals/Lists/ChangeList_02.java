package Exercise_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChangeList_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numberList = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        String input = scanner.nextLine();

        while (!input.equals("end")){
            List<String> commands = Arrays.stream(input.split(" ")).collect(Collectors.toList());
            String command = commands.get(0);
            switch (command){
                case "Delete":
                    int numberDelete = Integer.parseInt(commands.get(1));
                    numberList.removeAll(Arrays.asList(numberDelete));
                    break;
                case "Insert":
                    int numberInsert = Integer.parseInt(commands.get(1));
                    int position = Integer.parseInt(commands.get(2));
                    numberList.add(position, numberInsert);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println(numberList.toString().replaceAll("[\\[\\],]", ""));
    }
}
