package softUni;

import java.util.ArrayList;
import java.util.List;

public class SoftUni {

    private int capacity;
    private List<Student> data;

    public SoftUni(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getCount() {
        return this.data.size();
    }

    public String insert(Student student) {
        String message = "";
        if (this.data.size() < capacity) {
            if (!this.data.contains(student)) {
                this.data.add(student);
                message = String.format("Added student %s %s.",
                        student.getFirstName(), student.getLastName());
            } else {
                message = "Student is already in the hall.";
            }
        } else {
            message = "The hall is full.";
        }
        return message;
    }

    public String remove(Student student) {
        String message = "";
        if (this.data.contains(student)) {
            this.data.remove(student);
            message = String.format("Removed student %s %s.",
                    student.getFirstName(), student.getLastName());
        } else {
            message = "Student not found.";
        }
        return message;
    }

    public Student getStudent(String firstName, String lastName) {
        return this.data.stream().filter(s ->
                s.getFirstName().equals(firstName) &&
                s.getLastName().equals(lastName)).findFirst().orElse(null);
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Hall size: %d", getCount()));
        sb.append(System.lineSeparator());
        for (Student student : this.data) {
            sb.append(student.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
