package ExamPreparetion_01.university;

import java.util.ArrayList;
import java.util.Collection;

public class University {

    private Collection<Student> students;
    public int capacity;

    public University(int capacity) {
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getStudentCount() {
        return this.students.size();
    }

    public String registerStudent(Student student) {
        String message = "";
        boolean ifEquals = false;
        for (Student currentStudent : this.students) {
            if (currentStudent.getFirstName().equals(student.getFirstName()) &&
                    currentStudent.getLastName().equals(student.getLastName())) {
                ifEquals = true;
                break;
            }
        }
        if (!ifEquals && this.students.size() < this.capacity) {
            this.students.add(student);
            message = String.format("Added student %s %s", student.getFirstName(), student.getLastName());
        } else if (ifEquals) {
            message = "Student is already in the ExamPreparetion_01.university";
        } else {
            message = "No seats in the ExamPreparetion_01.university";
        }
        return message;
    }

    public String dismissStudent(Student student) {
        boolean isFound = false;
        for (Student currentStudent : this.students) {
            if (currentStudent.getFirstName().equals(student.getFirstName())
                    && currentStudent.getLastName().equals(student.getLastName())) {
                this.students.remove(currentStudent);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            return String.format("Removed student %s %s", student.getFirstName(), student.getLastName());
        } else {
            return "Student not found";
        }
    }

    public Student getStudent(String firstName, String lastName) {
        Student student = null;
        for (Student currentStudent : this.students) {
            if (currentStudent.getFirstName().equals(firstName) &&
                    currentStudent.getLastName().equals(lastName)) {
                student = currentStudent;
                break;
            }
        }
        return student;
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Student currentStudent : this.students) {
            sb.append(String.format("==Student: First Name = %s, Last Name = %s, Best Subject = %s",
                    currentStudent.getFirstName(), currentStudent.getLastName(), currentStudent.getBestSubject()));
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
