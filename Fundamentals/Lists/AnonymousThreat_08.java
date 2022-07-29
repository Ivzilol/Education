package Exercise_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AnonymousThreat_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> names = Arrays.stream(scanner.nextLine().split(" ")).collect(Collectors.toList());

        String commands = scanner.nextLine();

        while (!commands.equals("3:1")) {
            if (commands.contains("merge")) {
                int startIndex = Integer.parseInt(commands.split(" ")[1]);
                int endIndex = Integer.parseInt(commands.split(" ")[2]);
                if (startIndex < 0) {
                    startIndex = 0;
                }
                if (endIndex > names.size() - 1) {
                    endIndex = names.size() - 1;
                }
                boolean isIndexIsValid = startIndex <= names.size() - 1 && endIndex >= 0 && startIndex < endIndex;
                if (isIndexIsValid) {
                    String result = "";
                    for (int index = startIndex; index <= endIndex; index++) {
                        String currentElement = names.get(index);
                        result += currentElement;
                    }
                    for (int index = startIndex; index <= endIndex; index++) {
                        names.remove(startIndex);
                    }
                    names.add(startIndex, result);
                }
            } else if (commands.contains("divide")) {
                int index = Integer.parseInt(commands.split(" ")[1]);
                int parts = Integer.parseInt(commands.split(" ")[2]);
                String elementsDivide = names.get(index);
                names.remove(index);
                int partSize = elementsDivide.length() / parts;
                int beginIndex = 0;
                for (int i = 1; i < parts; i++) {
                    names.add(index, elementsDivide.substring(beginIndex, beginIndex + partSize));
                    index++;
                    beginIndex += partSize;
                }
                names.add(index, elementsDivide.substring(beginIndex));
            }
            commands = scanner.nextLine();
        }
        for (String text : names) {
            System.out.print(text + " ");
        }
    }
}
