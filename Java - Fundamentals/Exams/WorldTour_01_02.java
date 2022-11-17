package Exam_preparation_02;

import java.util.Scanner;

public class WorldTour_01_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String travelList = scanner.nextLine();
        String input = scanner.nextLine();

        while (!input.equals("Travel")) {
            String command = input.split(":")[0];
            switch (command) {
                case "Add Stop":
                    int indexForAdd = Integer.parseInt(input.split(":")[1]);
                    String addText = input.split(":")[2];
                    String firstPart = travelList.substring(0, indexForAdd);
                    String secondPart = travelList.substring(indexForAdd);
                    travelList = firstPart.concat(addText).concat(secondPart);
                    System.out.println(travelList);
                    break;
                case "Remove Stop":
                    int firstIndex = Integer.parseInt(input.split(":")[1]);
                    int secondIndex = Integer.parseInt(input.split(":")[2]);
                    if (firstIndex >= 0 && firstIndex < travelList.length() && secondIndex >= 0
                            && secondIndex < travelList.length()) {
                        String textRemove = travelList.substring(firstIndex, secondIndex + 1);
                        travelList = travelList.replace(textRemove, "");
                    }
                    System.out.println(travelList);
                    break;
                case "Switch":
                    String oldString = input.split(":")[1];
                    String newString = input.split(":")[2];
                    if (travelList.contains(oldString)) {
                        travelList = travelList.replaceAll(oldString, newString);
                    }
                    System.out.println(travelList);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.printf("Ready for world tour! Planned stops: %s", travelList);
    }
}
