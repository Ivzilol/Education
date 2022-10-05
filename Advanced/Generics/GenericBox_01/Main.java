package Exercises_07.GenericBox_01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Box<String> box = new Box<>();
        for (int i = 0; i < n; i++) {
            String text = scanner.nextLine();
            box.add(text);
        }
        System.out.println(box);
    }
}
