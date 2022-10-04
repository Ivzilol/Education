package Lab_07.ListUtilities_04;

import java.util.Collections;
import java.util.List;

public class ListUtils {

    public static <T extends Comparable<T>> T getMax(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return Collections.max(list);

    }

    public static <T extends Comparable<T>> T getMin(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException();
        }
        T currentMin = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            T currentElement = list.get(i);
            if (currentMin.compareTo(currentElement) > 0) {
                currentMin = currentElement;
            }
        }
        return currentMin;
    }
}
