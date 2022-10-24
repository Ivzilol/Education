package Exam_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DeckOfCards_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> deckCards = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .collect(Collectors.toList());

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String commands = scanner.nextLine();
            String firstCommand = commands.split(",\\s+")[0];
            String finalFirstCommand = firstCommand + ",";
            if (finalFirstCommand.equals("Add,")) {
                Add(deckCards, commands);
            } else if (finalFirstCommand.equals("Remove,")) {
                remove(deckCards, commands);
            } else if (finalFirstCommand.equals("Remove At,")) {
                RemoveAt(deckCards, commands);
            } else if (finalFirstCommand.equals("Insert,")) {
                insert(deckCards, commands);
            }
        }
        print(deckCards);
    }

    private static void print(List<String> deckCards) {
        for (int index = 0; index < deckCards.size(); index++) {
            if (index != deckCards.size() - 1) {
                System.out.print(deckCards.get(index) + ", ");
            } else {
                System.out.print(deckCards.get(index));
            }
        }
    }

    private static void insert(List<String> deckCards, String commands) {
        int indexForAdd = Integer.parseInt(commands.split(",\\s+")[1]);
        String cardForAdd = commands.split(",\\s+")[2];
        if (indexForAdd < 0 || indexForAdd > deckCards.size()) {
            System.out.println("Index out of range");
            return;
        }
        if (deckCards.contains(cardForAdd)) {
            System.out.println("Card is already added");
            return;
        }
        deckCards.add(indexForAdd, cardForAdd);
        System.out.println("Card successfully added");
    }

    private static void RemoveAt(List<String> deckCards, String commands) {
        int indexForRemove = Integer.parseInt(commands.split(",\\s+")[1]);
        if (indexForRemove < 0 || indexForRemove > deckCards.size()) {
            System.out.println("Index out of range");
        } else {
            deckCards.remove(indexForRemove);
            System.out.println("Card successfully removed");
        }
    }

    private static void remove(List<String> deckCards, String commands) {
        String cardNameForRemove = commands.split(",\\s+")[1];
        if (deckCards.contains(cardNameForRemove)) {
            deckCards.remove(cardNameForRemove);
            System.out.println("Card successfully removed");
        } else {
            System.out.println("Card not found");
        }
    }

    private static void Add(List<String> deckCards, String commands) {
        String cardName = commands.split(",\\s+")[1];
        if (!deckCards.contains(cardName)) {
            deckCards.add(cardName);
            System.out.println("Card successfully added");
        } else {
            System.out.println("Card is already in the deck");
        }
    }
}
