package Lab_06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Students2_06 {
    static class Student {
        String firstName;
        String lastName;
        String age;
        String town;

        public String getFirstName() {
            return this.firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        public String getTown() {
            return this.town;
        }

        public String getAge() {
            return this.age;
        }

        public Student(String firstName, String lastName, String age, String town) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.town = town;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setTown(String town) {
            this.town = town;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Student> studentList = new ArrayList<>();
        while (!input.equals("end")) {
            List<String> inputStudentList = List.of(input.split(" "));
            String firstName = inputStudentList.get(0);
            String lastName = inputStudentList.get(1);
            String age = inputStudentList.get(2);
            String town = inputStudentList.get(3);

            Student currentStudent = new Student(firstName, lastName, age, town);
            if (!IsStudentExisting(studentList, firstName, lastName))
                studentList.add(currentStudent);
            if (IsStudentExisting(studentList, firstName, lastName)) {
                Student student = getStudent(studentList, firstName, lastName);
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setAge(age);
                student.setTown(town);
            }
            input = scanner.nextLine();
        }
        String townPrint = scanner.nextLine();
        for (Student person : studentList) {
            if (person.getTown().equals(townPrint)) {
                System.out.printf("%s %s is %s years old\n", person.getFirstName(),
                        person.getLastName(), person.getAge());
            }
        }
    }

    private static boolean IsStudentExisting(List<Student> students, String firstName, String lastName) {
        for (Student student : students) {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }

    private static Student getStudent(List<Student> students, String firstName, String lastName) {
        Student existingStudent = null;
        for (Student student : students) {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                existingStudent = student;
            }
        }
        return existingStudent;
    }
}
