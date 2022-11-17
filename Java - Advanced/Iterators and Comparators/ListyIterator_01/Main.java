package Exercises_08.ListyIterator_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String createInput = scanner.nextLine();
        List<String> data = Arrays.stream(createInput.split("\\s+"))
                .skip(1).collect(Collectors.toList());

        ListyIterator listyIterator = new ListyIterator(data);

        String commands = scanner.nextLine();

        while (!commands.equals("END")) {
            switch (commands) {
                case "Move":
                    System.out.println(listyIterator.move());
                    break;
                case "Print":
                    listyIterator.print();
                    break;
                case "HasNext":
                    System.out.println(listyIterator.hasNext());
                    break;
            }
            commands = scanner.nextLine();
        }
    }
}
