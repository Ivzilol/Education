import java.util.*;

public class Demo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, List<String>> pianoPieces = new LinkedHashMap<>();
        fillMap(scanner, n, pianoPieces);
        service(scanner, pianoPieces);
        printMap(pianoPieces);
    }

    private static void service(Scanner scanner, Map<String, List<String>> pianoPieces) {
        String input = scanner.nextLine();
        while (!input.equals("Stop")) {
            switch (input.split("\\|")[0]) {
                case "Add":
                    addPiece(pianoPieces, input);
                    break;
                case "Remove":
                    removePiece(pianoPieces, input);
                    break;
                case "ChangeKey":
                    changeKey(pianoPieces, input);
                    break;
            }
            input = scanner.nextLine();
        }
    }

    private static void printMap(Map<String, List<String>> pianoPieces) {
        for (Map.Entry<String, List<String>> entry : pianoPieces.entrySet()) {
            System.out.printf("%s -> Composer: %s, Key: %s\n", entry.getKey(), entry.getValue().get(0),
                    entry.getValue().get(1));
        }
    }

    private static void changeKey(Map<String, List<String>> pianoPieces, String input) {
        String pieceForChange = input.split("\\|")[1];
        String newKey = input.split("\\|")[2];
        if (pianoPieces.containsKey(pieceForChange)) {
            pianoPieces.get(pieceForChange).set(1, newKey);
            System.out.printf("Changed the key of %s to %s!\n", pieceForChange, newKey);
        } else {
            System.out.printf("Invalid operation! %s does not exist in the collection.\n", pieceForChange);
        }
    }

    private static void removePiece(Map<String, List<String>> pianoPieces, String input) {
        String piecesForRemove = input.split("\\|")[1];
        if (pianoPieces.containsKey(piecesForRemove)) {
            pianoPieces.remove(piecesForRemove);
            System.out.printf("Successfully removed %s!\n", piecesForRemove);
        } else {
            System.out.printf("Invalid operation! %s does not exist in the collection.\n", piecesForRemove);
        }
    }

    private static void addPiece(Map<String, List<String>> pianoPieces, String input) {
        String pieces = input.split("\\|")[1];
        if (!pianoPieces.containsKey(pieces)) {
            List<String> piecesForAdd = new ArrayList<>();
            piecesForAdd.add(input.split("\\|")[2]);
            piecesForAdd.add(input.split("\\|")[3]);
            pianoPieces.put(pieces, piecesForAdd);
            System.out.printf("%s by %s in %s added to the collection!\n", input.split("\\|")[1],
                    input.split("\\|")[2], input.split("\\|")[3]);
        } else {
            System.out.printf("%s is already in the collection!\n", pieces);
        }
    }

    private static void fillMap(Scanner scanner, int n, Map<String, List<String>> pianoPieces) {
        for (int index = 0; index < n; index++) {
            String favoritePieces = scanner.nextLine();
            if (!pianoPieces.containsKey(favoritePieces.split("\\|")[0])) {
                List<String> composerAndKey = new ArrayList<>();
                composerAndKey.add(favoritePieces.split("\\|")[1]);
                composerAndKey.add(favoritePieces.split("\\|")[2]);
                pianoPieces.put(favoritePieces.split("\\|")[0], composerAndKey);
            }
        }
    }
}