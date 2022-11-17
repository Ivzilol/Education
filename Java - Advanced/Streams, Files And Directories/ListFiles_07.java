package Lab_04;

import java.io.File;
import java.util.Scanner;

public class ListFiles_07 {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\Simeon\\Desktop\\Advanced\\src\\Lab_04\\Files-and-Streams");
        if (file.exists()){
            if (file.isDirectory()){
                File[] files = file.listFiles();
                for (File f : files){
                    if (!f.isDirectory()){
                        System.out.printf("%s: [%s]\n", f.getName(), f.length());
                    }
                }
            }
        }
    }
}
