package Exercises_06.CompanyRoster_02;

public class Employee {
    private String name;


    private double salary;
    private String position;
    private String department;
    private String email;
    private int age;

    public Employee(String name, double salary, String position, String department) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.age = -1;
        this.email = "n/a";

    }

    public Employee(String name, double salary, String position, String department, String email
            , int age) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = email;
        this.age = age;
    }

    public Employee(String name, double salary, String position, String department, String email) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = email;
        this.age = -1;
    }

    public Employee(String name, double salary, String position, String department, int age) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.age = age;
        this.email = "n/a";
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name + " ").append(String.format("%.2f", salary) + " ")
                .append(email + " ").append(age);
        return builder.toString();
    }
}
