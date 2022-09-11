package Lab_04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExtractIntegers_04 {
    public static void main(String[] args) throws FileNotFoundException {

        String pathIn = "C:\\Users\\Simeon\\Desktop\\Advanced\\src\\Lab_04\\input.txt";
        String pathOut = "C:\\Users\\Simeon\\Desktop\\Advanced\\src\\Lab_04\\04.ExtractIntegersOutput.txt";
        Scanner scanner = new Scanner(new FileInputStream(pathIn));
        PrintWriter myFileOutput = new PrintWriter(new FileOutputStream(pathOut));
        while (scanner.hasNext()){
            if (scanner.hasNextInt()){
                int myNumber = scanner.nextInt();
//                System.out.println(scanner.nextInt());
                myFileOutput.println(myNumber);
            }
            scanner.next();
        }
        myFileOutput.close();
    }
}
