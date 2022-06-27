package Traning05;

import java.util.Scanner;

public class StreamOfLetters_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int countC = 0;
        int countO = 0;
        int countN = 0;
        String word = "";
        String word2 = "";


        while (!input.equals("End")){
            char x = input.charAt(0);
            if (((x >= 'a' && x <= 'z')) || ((x >= 'A' && x <= 'Z')) && Character.isAlphabetic(x)){
                if ((x == 'c' || x == 'o' || x == 'n') && (countC == 0 || countO == 0 || countN == 0)){
                    switch (x){
                        case 'c':
                            if (countC == 1){
                                word += x;
                                break;
                            }else {
                                countC++;
                            }
                            break;
                        case 'o':
                            if (countO == 1){
                                word += x;
                                break;
                            }else {
                                countO++;

                            }
                            break;
                        case 'n':
                            if (countN == 1){
                                word += x;
                                break;
                            }else {
                                countN++;

                            }
                            break;

                    }
                }else {
                    word += x;
                }
                if ((x == 'c' || x == 'o' || x == 'n' ) && (countC == 1 && countO == 1 && countN == 1)){
                    word += " ";
                    word2 += word;
                    countC = 0;
                    countO = 0;
                    countN = 0;
                    word = "";
                }
            }

            input = scanner.nextLine();

        }
        System.out.println(word2);
    }
}
