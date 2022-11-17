package Exercises_05;

import java.util.Scanner;
import java.util.function.Consumer;

public class ConsumerPrint_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Function< приема, връща> -> apply
        //Consumer <приема> -> void -> accept
        //Supplier<връща> -> get
        //Predicate<приема> -> връща true / false -> test
        //BiFunction<приема1, приема2, връща> -> apply

        String input = scanner.nextLine();
        String[] names = input.split("\\s+");

        /*Consumer<String> printName = name -> System.out.println(name);
        for (String name : names) {
            printName.accept(name);
        }*/
        Consumer<String[]> printNames = array -> {
            for (String name : names){
                System.out.println(name);
            }
        };
        printNames.accept(names);
    }
}
