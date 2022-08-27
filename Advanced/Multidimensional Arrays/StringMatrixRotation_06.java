package Exercises_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatrixRotation_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<String> commandList = new ArrayList<>();
        while (!input.equals("END")){
            String currentElement = input;
            commandList.add(currentElement);
            input = scanner.nextLine();
        }
        String rotateCommand = commandList.get(0);
        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rotateCommand);
        int degreesRotate = 0;
        if (matcher.find()){
            degreesRotate = Integer.parseInt(matcher.group());
        }
        commandList.remove(0);
        int matrixSize = 0;
        for (String word : commandList){
            int sizeCurrentWord = word.length();
            if (sizeCurrentWord > matrixSize){
                matrixSize = sizeCurrentWord;
            }
        }
        char[][] matrix = new char[commandList.size()][matrixSize];
        for (int index = 0; index < matrix.length; index++) {
            char[] arr = commandList.get(index).toCharArray();
            matrix[index] = arr;
        }
        while (degreesRotate >= 360){
            degreesRotate = degreesRotate % 360;
        }
        if (degreesRotate == 90){
            char[][] newMatrix = new char[matrixSize][commandList.size()];
            int i = 0;
            for (int c = matrix.length - 1; c >= 0; c--) {
                int j = 0;
                for (int r = 0; r < matrix[i].length; r++) {
                    newMatrix[r][c] = matrix[i][j];
                    j++;
                }
                i++;
            }
            for (int row = 0; row < newMatrix.length; row++) {
                for (int col = 0; col < newMatrix[row].length; col++) {
                    if (newMatrix[row][col] == 0){
                        System.out.print(" ");
                        continue;
                    }
                    System.out.print(newMatrix[row][col]);
                }
                System.out.println();
            }
        }else if (degreesRotate == 180){
            char[][] newMatrix = new char[commandList.size()][matrixSize];
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    newMatrix[matrix.length - 1 - row][col] = matrix[row][matrix[row].length - 1 - col];
                }
            }
            for (int row = 0; row < newMatrix.length; row++) {
                for (int col = 0; col < newMatrix[row].length; col++) {
                    if (newMatrix[row][col] == 0){
                        System.out.print(" ");
                        continue;
                    }
                    System.out.print(newMatrix[row][col]);
                }
                System.out.println();
            }
        }else if (degreesRotate == 270){
            char[][] newMatrix = new char[matrixSize][commandList.size()];
            int i = 0;
            for (int col = 0; col < newMatrix[commandList.size()].length; col++) {
                int j = 0;
                for (int row = newMatrix.length - 1; row >= 0 ; row--) {
                    if (j < matrix[i].length){
                        newMatrix[row][col] = matrix[i][j];
                    }
                    j++;
                }
                i++;
            }
            for (int row = 0; row < newMatrix.length; row++) {
                for (int col = 0; col < newMatrix[row].length; col++) {
                    if (newMatrix[row][col] == 0){
                        System.out.print(" ");
                        continue;
                    }
                    System.out.print(newMatrix[row][col]);
                }
                System.out.println();
            }
        }else {
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    if (matrix[row][col] == 0){
                        System.out.print(" ");
                        continue;
                    }
                    System.out.print(matrix[row][col]);
                }
                System.out.println();
            }
        }
    }
}
