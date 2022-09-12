package Lab_04;

import java.io.*;
import java.util.Scanner;

public class WriteEveryThirdLine_05 {
    public static void main(String[] args) throws IOException {

        String inPath = "C:\\Users\\Simeon\\Desktop\\Advanced\\src\\Lab_04\\input.txt";
        String outPath = "C:\\Users\\Simeon\\Desktop\\Advanced\\src\\Lab_04\\05.WriteEveryThirdLineOutput.txt";

        Scanner in = new Scanner(new FileReader(inPath));
        PrintWriter out = new  PrintWriter(new FileWriter(outPath));
        int counter = 1;
        String line = in.nextLine();
        while (in.hasNextLine()){
            if (counter % 3 == 0){
                out.println(line);
            }
            counter++;
            line = in.nextLine();
        }
        out.close();
    }
}
