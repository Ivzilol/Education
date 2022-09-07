package Exercises_03;

import java.util.*;

public class HandsOfCards_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, Set<String>> players = new LinkedHashMap<>();
        while (!input.equals("JOKER")) {
            String playerName = input.split(":\\s+")[0];
            String cards = input.split(":\\s+")[1];
            String[] cardsArr = cards.split(", ");
            Set<String> cardsSet = new LinkedHashSet<>();
            cardsSet.addAll(Arrays.asList(cardsArr));
            if (!players.containsKey(playerName)) {
                players.put(playerName, cardsSet);
            } else {
                Set<String> currentCards = players.get(playerName);
                currentCards.addAll(cardsSet);
                players.put(playerName, currentCards);
            }
            input = scanner.nextLine();
        }
        players.entrySet().forEach(entry -> {
            String name = entry.getKey();
            Set<String> cards = entry.getValue();
            int points = getCardsPoits(cards);
            System.out.printf("%s: %d\n", name, points);
        });
    }

    private static int getCardsPoits(Set<String> cards) {
        Map<Character, Integer> symbolsValue = getSymbolsValues();
        int sumPoints = 0;

        for (String card : cards) {
            int points = 0;
            if (card.startsWith("10")) {
                char type = card.charAt(2);
                points = 10 * symbolsValue.get(type);
            } else {
                char power = card.charAt(0);
                char type = card.charAt(1);
                points = symbolsValue.get(power) * symbolsValue.get(type);
            }
            sumPoints += points;
        }
        return sumPoints;
    }

    private static Map<Character, Integer> getSymbolsValues() {
        Map<Character, Integer> charactersValues = new HashMap<>();
        charactersValues.put('2', 2);
        charactersValues.put('3', 3);
        charactersValues.put('4', 4);
        charactersValues.put('5', 5);
        charactersValues.put('6', 6);
        charactersValues.put('7', 7);
        charactersValues.put('8', 8);
        charactersValues.put('9', 9);
        charactersValues.put('J', 11);
        charactersValues.put('Q', 12);
        charactersValues.put('K', 13);
        charactersValues.put('A', 14);
        charactersValues.put('S', 4);
        charactersValues.put('H', 3);
        charactersValues.put('D', 2);
        charactersValues.put('C', 1);
        return charactersValues;

    }
}
