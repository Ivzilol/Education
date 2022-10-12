package Exercises_08.ComparingObjects_05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Person> peopleList = new ArrayList<>();
        int countAllPeope = 0;
        while (!input.equals("END")){
            String[] people = input.split("\\s+");
            String name = people[0];
            int age = Integer.parseInt(people[1]);
            String town = people[2];
            Person person = new Person(name, age, town);
            peopleList.add(person);
            countAllPeope++;
            input = scanner.nextLine();
        }
        int n = Integer.parseInt(scanner.nextLine());
        Person searchPerson = peopleList.get(n - 1);
        peopleList.remove(n - 1);
        int count = 0;
        for (Person person : peopleList){
            if (searchPerson.compareTo(person) == 0){
                count++;
            }
        }
        if (count == 0){
            System.out.println("No matches");
        }else {
            System.out.printf("%d %d %d", count + 1, countAllPeope  - count - 1, countAllPeope);
        }
    }
}
