package Lab_04;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class WriteToFile_02 {
    public static void main(String[] args) {

        String pathIn = "C:\\Users\\Simeon\\Desktop\\Advanced\\src\\Lab_04\\input.txt";
        String pathOut = "C:\\Users\\Simeon\\Desktop\\Advanced\\src\\Lab_04\\02.WriteToFileOutput.txt";
        Set<Character> forbiddenSymbols = new HashSet<>();
        Collections.addAll(forbiddenSymbols, '.', ',', '!', '?');
        try (FileInputStream in = new FileInputStream(pathIn);
             FileOutputStream out = new FileOutputStream(pathOut)) {
            int oneByte = in.read();
            while (oneByte >= 0){
                char myByteAsChar = (char) oneByte;
                if (!forbiddenSymbols.contains(myByteAsChar)){
                    out.write(oneByte);
                }
                oneByte = in.read();
            }
        }catch (IOException e) {
        }
    }
}
