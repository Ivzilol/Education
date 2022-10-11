package Lab_08.ComparableBook_03;

public class Main {
    public static void main(String[] args) {
        Book firstBook = new Book("Clien Code", 2000, "Uncle Bob");
        Book secondBook = new Book("Thinking in Java", 2006, "Bruce Eckle");
        System.out.println(firstBook.compareTo(secondBook));
    }
}
