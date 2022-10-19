package Lab_05.Shapes;

public class Main {
    public static void main(String[] args) {
        Shape1 shapes = new Rectangle(4, 5);
        System.out.println(shapes.calculateArea());
        System.out.println(shapes.calculatePerimeter());

        shapes = new Circle(5);
        System.out.println(shapes.calculatePerimeter());
        System.out.println(shapes.calculateArea());

    }
}
