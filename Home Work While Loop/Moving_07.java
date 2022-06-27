package Exercises_05;

import java.util.Scanner;

public class Moving_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int width = Integer.parseInt(scanner.nextLine());
        int length = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());

        int volume = width * length * height;

        while (volume > 0) {
            String input = scanner.nextLine();
            if (input.equals("Done")) {
                break;
            }
            int boxVolume = Integer.parseInt(input);
            volume -= boxVolume;

        }
        if (volume > 0) {
            System.out.printf("%d Cubic meters left.", volume);
        } else {
            System.out.printf("No more free space! You need %d Cubic meters more.", Math.abs(volume));
        }

    }
}
