package Lab_01.PointInRectangle_02;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coordinatesOfRectangle = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int xBottomLeft = coordinatesOfRectangle[0];
        int yBottomLeft = coordinatesOfRectangle[1];

        Point bottomLeft = new Point(xBottomLeft, yBottomLeft);

        int xTopRight = coordinatesOfRectangle[2];
        int yTopRight = coordinatesOfRectangle[3];

        Point topRight = new Point(xTopRight, yTopRight);

        Rectangle rectangle = new Rectangle(bottomLeft, topRight);

        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            int[] pointCoordinates = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Point current = new Point(pointCoordinates[0], pointCoordinates[1]);
            System.out.println(rectangle.contains(current));
        }
    }
}
