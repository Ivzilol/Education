package Exercise_05;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SoftUniCoursePlanning_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> lessons = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.contains("course start")) {
            List<String> commands = Arrays.stream(input.split(":")).collect(Collectors.toList());
            if (input.contains("Add")) {
                String lessonTitle = commands.get(1);
                lessons.add(lessonTitle);
            } else if (input.contains("Insert")) {
                String lessonTitle = commands.get(1);
                int index = Integer.parseInt(commands.get(2));
                if (!lessons.contains(lessonTitle)) {
                    lessons.add(index, lessonTitle);
                } else {
                    break;
                }
            } else if (input.contains("Remove")) {
                String lessonTitle = commands.get(1);
                lessons.removeIf(lessonsInList -> lessonsInList.equals(lessonTitle));
            } else if (input.contains("Swap")) {
                String swapTitleOne = commands.get(1);
                String swapTitleTwo = commands.get(2);
                if (lessons.contains(swapTitleOne) && lessons.contains(swapTitleTwo)) {
                    int indexTitleOne = lessons.indexOf(swapTitleOne);
                    int indexTitleTwo = lessons.indexOf(swapTitleTwo);
                    lessons.set(indexTitleOne, swapTitleTwo);
                    lessons.set(indexTitleTwo, swapTitleOne);
                    if (lessons.contains(swapTitleOne + "-Exercise")) {
                        String name = swapTitleOne + "-Exercise";
                        lessons.remove(swapTitleTwo + "-Exercise");
                        if (indexTitleTwo + 1 <= lessons.size() - 1) {
                            lessons.add(indexTitleTwo + 1, name);
                        } else {
                            lessons.add(name);
                        }
                    } else if (lessons.contains(swapTitleTwo + "-Exercise")) {
                        String name = swapTitleTwo + "-Exercise";
                        lessons.remove(swapTitleTwo + "-Exercise");
                        if (indexTitleOne + 1 <= lessons.size() - 1) {
                            lessons.add(indexTitleOne + 1, name);
                        } else {
                            lessons.add(name);
                        }
                    }
                }
            } else if (input.contains("Exercise")) {
                String titleLesson = commands.get(1);
                String titleExercise = titleLesson + "-Exercise";
                if (lessons.contains(titleLesson)) {
                    if (!lessons.contains(titleLesson)) {
                        int indexTitleLesson = lessons.indexOf(titleLesson);
                        if (indexTitleLesson < lessons.size() - 1) {
                            lessons.add(indexTitleLesson, titleExercise);
                        } else {
                            lessons.add(titleExercise);
                        }
                    }
                } else {
                    lessons.add(titleLesson);
                    lessons.add(titleExercise);
                }
            }
            input = scanner.nextLine();
        }
        int count = 0;
        for (String lessonsInList : lessons) {
            count++;
            System.out.printf("%d.", count);
            System.out.println(lessonsInList);
        }
    }
}
