package Exercises_03;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountSymbols_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<Character, Integer> symbolsMap = new TreeMap<>();
        for (int index = 0; index < input.length(); index++) {
            char currentSymbol = input.charAt(index);
            if (!symbolsMap.containsKey(currentSymbol)) {
                symbolsMap.put(currentSymbol, 1);
            } else {
                symbolsMap.put(currentSymbol, symbolsMap.get(currentSymbol) + 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : symbolsMap.entrySet()) {
            System.out.printf("%c: %d time/s\n", entry.getKey(), entry.getValue());
        }
    }
}
