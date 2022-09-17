package Lab_05;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.*;

public class FunctionTypes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> myList = new ArrayList<>();
        myList.add("5");
        myList.add("7");
        myList.add("12");

        Function<String, Integer> func = e -> Integer.parseInt(e);
        func.apply("12");
        Function<Integer, Integer> square = e -> e * e;
        square.apply(12);

        BiFunction<Integer, Integer, String > sum = (x, y) -> "Sum is" + (x + y);

        Consumer<Integer> println = e -> System.out.println(e);
        println.accept(12);

        Supplier<Integer> genRandomInt = () -> new Random().nextInt(51);
        int rnd = genRandomInt.get();

        Predicate<Integer> checkIfEven = e -> e % 2 == 0;
        checkIfEven.test(13);

        BiPredicate<Integer, Integer> myBePredicate = (a, b) -> a > b;

        BiConsumer<String, Integer> myBiConsumer = (String name, Integer age) ->
                System.out.println(name + " " + age);
    }

}
