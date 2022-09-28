package Exercises_06.OpinionPoll_01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Person> peopleList = new ArrayList<>();
        for (int index = 0; index < n; index++) {
            String personalInformation = scanner.nextLine();
            String name = personalInformation.split("\\s+")[0];
            int age = Integer.parseInt(personalInformation.split("\\s+")[1]);
            Person person = new Person(name, age);
            if (age > 30){
                peopleList.add(person);
            }
        }
        peopleList.sort(Comparator.comparing(person -> person.getName()));
        peopleList.forEach(person -> System.out.printf("%s - %d\n", person.getName(), person.getAge()));
    }
}
