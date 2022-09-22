package Exercises_05;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindTheSmallestElement_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Function< приема, връща> -> apply
        //Consumer <приема> -> void -> accept
        //Supplier<връща> -> get
        //Predicate<приема> -> връща true / false -> test
        //BiFunction<приема1, приема2, връща> -> apply

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Consumer<List<Integer>> printMinElement = list -> System.out.println(list.lastIndexOf(Collections.min(list)));
        printMinElement.accept(numbers);
        //indexOf -> връща първия индекс на който срещаме даден елемент
        //lastIndexOf -> връща последния индекс на който е срещнат даден елемент
        /*Function<List<Integer>, Integer> getIndexOfMinElement = list ->
                list.lastIndexOf(Collections.min(list));
        System.out.println(getIndexOfMinElement.apply(numbers));*/
    }
}
