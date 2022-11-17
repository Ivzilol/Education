package Traning_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LadyBugs_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int fieldSize = Integer.parseInt(scanner.nextLine());
        int[] fieldArr = new int[fieldSize];
        int[] bugsIndex = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        for (int bugIndex : bugsIndex) {
            if (bugIndex >= 0 && bugIndex <= fieldArr.length - 1) {
                fieldArr[bugIndex] = 1;
            }
        }

        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String[] commands = input.split(" ");
            int firstCommand = Integer.parseInt(commands[0]);
            String secondCommand = commands[1];
            int thirdCommand = Integer.parseInt(commands[2]);
            if (firstCommand >= 0 && firstCommand <= fieldArr.length - 1 && fieldArr[firstCommand] == 1) {
                fieldArr[firstCommand] = 0;
                if (secondCommand.equals("right")) {
                    firstCommand += thirdCommand;
                    while (firstCommand <= fieldArr.length - 1 && fieldArr[firstCommand] == 1) {
                        firstCommand += thirdCommand;
                    }
                    if (firstCommand <= fieldArr.length - 1) {
                        fieldArr[firstCommand] = 1;
                    }
                } else if (secondCommand.equals("left")) {
                    firstCommand -= thirdCommand;
                    while (firstCommand >= 0 && fieldArr[firstCommand] == 1) {
                        firstCommand -= thirdCommand;
                    }
                    if (firstCommand >= 0 && firstCommand <= fieldArr.length - 1) {
                        fieldArr[firstCommand] = 1;
                    }
                }
            }
            input = scanner.nextLine();
        }
        for (int number : fieldArr)
            System.out.print(number + " ");
    }
}
