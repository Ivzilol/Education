package Exam_preparation_02;

import java.util.*;

public class ThePianist_03_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberPieces = Integer.parseInt(scanner.nextLine());
        Map<String, List<String>> listPieces = new LinkedHashMap<>();
        for (int i = 0; i < numberPieces; i++) {
            String input = scanner.nextLine();
            String pieces = input.split("\\|")[0];
            String composer = input.split("\\|")[1];
            String key = input.split("\\|")[2];
            List<String> composerAndKey = new ArrayList<>();
            composerAndKey.add(composer);
            composerAndKey.add(key);
            listPieces.put(pieces, composerAndKey);
        }
        String input = scanner.nextLine();
        while (!input.equals("Stop")) {
            String command = input.split("\\|")[0];
            switch (command) {
                case "Add":
                    String pieces = input.split("\\|")[1];
                    String composer = input.split("\\|")[2];
                    String key = input.split("\\|")[3];
                    List<String> composerAndKey = new ArrayList<>();
                    composerAndKey.add(composer);
                    composerAndKey.add(key);
                    if (!listPieces.containsKey(pieces)) {
                        listPieces.put(pieces, composerAndKey);
                        System.out.printf("%s by %s in %s added to the collection!\n", pieces, composer, key);
                    } else {
                        System.out.printf("%s is already in the collection!\n", pieces);
                    }
                    break;
                case "Remove":
                    String removePieces = input.split("\\|")[1];
                    if (listPieces.containsKey(removePieces)) {
                        listPieces.remove(removePieces);
                        System.out.printf("Successfully removed %s!\n", removePieces);
                    } else {
                        System.out.printf("Invalid operation! %s does not exist in the collection.\n", removePieces);
                    }
                    break;
                case "ChangeKey":
                    String oldPieces = input.split("\\|")[1];
                    String newPieces = input.split("\\|")[2];
                    if (listPieces.containsKey(oldPieces)) {
                        listPieces.get(oldPieces).set(1, newPieces);
                        System.out.printf("Changed the key of %s to %s!\n", oldPieces, newPieces);
                    } else {
                        System.out.printf("Invalid operation! %s does not exist in the collection.\n", oldPieces);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        for (Map.Entry<String, List<String>> stringListEntry : listPieces.entrySet()) {
            System.out.printf("%s -> Composer: %s, Key: %s\n", stringListEntry.getKey(),
                    stringListEntry.getValue().get(0), stringListEntry.getValue().get(1));
        }
    }
}
