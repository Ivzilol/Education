package Traning04;

import java.util.Scanner;

public class Hospital_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = Integer.parseInt(scanner.nextLine());
        int doctors = 7;
        int numberPatients = 0;
        int examinedPatients = 0;
        int count = 0;

        for (int i = 1; i <= days ; i++) {
            int patients = Integer.parseInt(scanner.nextLine());
            if (i % 3 == 0 && examinedPatients < numberPatients){
                doctors ++;

            }
            numberPatients += patients;
            if (doctors >= patients){
                count = 0;
            }else{
                count = patients - doctors;
                examinedPatients += count;
            }


        }

        System.out.printf("Treated patients: %d.%n", (numberPatients - examinedPatients));
        System.out.printf("Untreated patients: %d.", examinedPatients);
    }
}
