package Exercise_06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Students_05 {
    static class Student {
        String firstName;
        String lastName;
        double grade;

        public Student (String firstName, String lastName, double grade) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.grade = grade;
        }
        public double getGrade() {
            return this.grade;
        }
        @Override
        public String toString (){
            return String.format("%s %s: %.2f", this.firstName, this.lastName, this.grade);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Student> studentList = new ArrayList<>();
        for (int row = 1; row <= n ; row++) {
            String personalInfo = scanner.nextLine();
            String firstName = personalInfo.split(" ")[0];
            String lastName = personalInfo.split(" ")[1];
            double grade = Double.parseDouble(personalInfo.split(" ")[2]);

            Student student = new Student(firstName, lastName, grade);
            studentList.add(student);
        }
        studentList.sort(Comparator.comparingDouble(Student :: getGrade).reversed());
        for (Student students : studentList){
            System.out.println(students.toString());
        }
    }
}
