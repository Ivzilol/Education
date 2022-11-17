package Lab_08.BookComparator_04;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    @Override
    public int compare(Book firstBook, Book secondBook) {
        int result = firstBook.getTitle().compareTo(secondBook.getTitle());
        if (result == 0){
            result = Integer.compare(firstBook.getYear(), secondBook.getYear());
        }
        return result;
    }
}
