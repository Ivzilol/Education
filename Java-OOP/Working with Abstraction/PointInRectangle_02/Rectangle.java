package Lab_01.PointInRectangle_02;

public class Rectangle {

    private Point bottomLeft;
    private Point topRight;

    public Rectangle(Point bottomLeft, Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public boolean contains (Point point){
        boolean containsX = point.getX() >= bottomLeft.getX() &&
                point.getX() <= topRight.getX();
        boolean containsY = point.getY() >= bottomLeft.getY() &&
                point.getY() <= topRight.getY();

        return containsX && containsY;
    }
}
