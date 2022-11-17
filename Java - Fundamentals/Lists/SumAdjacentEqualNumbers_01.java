package Lab_05;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SumAdjacentEqualNumbers_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> numbersList = Arrays.stream(scanner.nextLine().split(" "))
                .map(Double::parseDouble).collect(Collectors.toList());

        for (int index = 0; index < numbersList.size() - 1; index++) {
            double firstNumber = numbersList.get(index);
            double secondNumber = numbersList.get(index + 1);
            if (firstNumber == secondNumber){
                numbersList.set(index, numbersList.get(index) + numbersList.get(index + 1));
                numbersList.remove(index + 1);
                index = -1;

            }

        }


        System.out.println(joinElements(numbersList, " "));
    }
    private static String joinElements(List<Double> list, String delimiter){
        String result = "";
        for (Double num : list){
            DecimalFormat df = new DecimalFormat("0.# ");
            String format = df.format(num);
            result += format;
        }
        return result;
    }
}
