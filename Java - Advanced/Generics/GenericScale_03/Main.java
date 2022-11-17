package Lab_07.GenericScale_03;

public class Main {
    public static void main(String[] args) {
        Scale<Integer> scale = new Scale<>(12, 11);
        System.out.println(scale.getHeavier());
    }
}
