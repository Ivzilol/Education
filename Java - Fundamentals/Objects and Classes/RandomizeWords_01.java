package Lab_06;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RandomizeWords_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<String> textSize = Arrays.stream(scanner.nextLine().split(" ")).collect(Collectors.toList());

        while (!textSize.isEmpty()) {
            Random text = new Random();
            int randomIndex = text.nextInt(textSize.size());
            String word = textSize.get(randomIndex);
            System.out.println(word);
            textSize.remove(randomIndex);
        }
    }
}
