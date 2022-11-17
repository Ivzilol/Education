package Exercise_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PokemonDontGo_09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbersList = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        int sumRemoveElements = 0;
        while (numbersList.size() > 0) {
            int index = Integer.parseInt(scanner.nextLine());
            if (index < 0) {
                int firstElement = numbersList.get(0);
                sumRemoveElements += firstElement;
                int lastElement = numbersList.get(numbersList.size() - 1);
                numbersList.set(0, lastElement);
                modifyList(numbersList, firstElement);
            } else if (index > numbersList.size() - 1) {
                int firstElement = numbersList.get(0);
                int lastElement = numbersList.get(numbersList.size() - 1);
                sumRemoveElements += lastElement;
                numbersList.set(numbersList.size() - 1, firstElement);
                modifyList(numbersList, lastElement);
            } else if (index >= 0 && index <= numbersList.size() - 1) {
                int removeElement = numbersList.get(index);
                sumRemoveElements += removeElement;
                numbersList.remove(index);
                modifyList(numbersList, removeElement);
            }
        }
        System.out.println(sumRemoveElements);
    }

    public static void modifyList(List<Integer> numberList, int removedElement) {
        for (int index = 0; index <= numberList.size() - 1; index++) {
            int currentElement = numberList.get(index);
            if (currentElement <= removedElement) {
                currentElement += removedElement;
            } else {
                currentElement -= removedElement;
            }
            numberList.set(index, currentElement);
        }
    }
}
