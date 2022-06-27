package Exercise_03;

import java.util.Objects;
import java.util.Scanner;

public class CommonElements_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstRow = scanner.nextLine();
        String secondRow = scanner.nextLine();

        String[] firstArr = firstRow.split(" ");
        String[] secondArr = secondRow.split(" ");

        for (String item : secondArr) {
            for (String item2 : firstArr) {
                if (Objects.equals(item, item2)) {
                    System.out.printf("%s ", item);
                }
            }
        }
    }
}
