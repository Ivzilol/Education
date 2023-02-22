package Exercises_03.animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String typeAnimal = scanner.nextLine();
        while (!typeAnimal.equals("Beast!")) {
            String[] animals = scanner.nextLine().split("\\s+");
            String animalName = animals[0];
            int animalAge = Integer.parseInt(animals[1]);
            String gender = animals[2];
            try {
                switch (typeAnimal) {
                    case "Cat":
                        Cat cat = new Cat(animalName, animalAge, gender);
                        System.out.print(cat);
                        break;
                    case "Dog":
                        Dog dog = new Dog(animalName, animalAge, gender);
                        System.out.print(dog);
                        break;
                    case "Frog":
                        Frog frog = new Frog(animalName, animalAge, gender);
                        System.out.print(frog);
                        break;
                    case "Kittens":
                        Kitten kitten = new Kitten(animalName, animalAge);
                        System.out.print(kitten);
                        break;
                    case "Tomcat":
                        Tomcat tomcat = new Tomcat(animalName, animalAge);
                        System.out.print(tomcat);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            typeAnimal = scanner.nextLine();
        }
    }
}
