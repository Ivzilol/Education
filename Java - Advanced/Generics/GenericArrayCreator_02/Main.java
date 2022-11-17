package Lab_07.GenericArrayCreator_02;

public class Main {
    public static void main(String[] args) {
        ArrayCreator arrayCreator = new ArrayCreator();
        String[] stringArray = ArrayCreator.<String>create(String.class, 5, "Pesho");
        Integer[] integerArray = ArrayCreator.create(5, 21);

    }

}
