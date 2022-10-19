package Lab_05.Shapes;

public abstract class Shape1 {
    private double perimeter;
    private double area;

    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
