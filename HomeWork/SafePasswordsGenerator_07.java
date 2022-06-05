package Traning06;

import java.util.Scanner;

public class SafePasswordsGenerator_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        int maxPass = Integer.parseInt(scanner.nextLine());
        int A = 35;
        int B = 64;
        int counter = 0;
        for (int i = 1; i <= a ; i++) {
            for (int j = 1; j <= b ; j++) {
                if (A > 55 ){
                    A = 35;
                }
                if (B > 96){
                    B = 64;
                }
                counter++;
                System.out.printf("%c%c%d%d%c%c|", A, B, i, j, B, A);
                A++;
                B++;
                if (counter == maxPass){
                    return;
                }
            }
        }
    }
}
