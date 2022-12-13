package ExamPreparetion_01.cafe;

import java.util.ArrayList;
import java.util.Collection;

public class Cafe {

    private Collection<Employee> employees;
    private String name;
    private int capacity;

    public Cafe(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (this.employees.size() < capacity) {
            this.employees.add(employee);
        }
    }

    public boolean removeEmployee(String name) {
        for (Employee employee : this.employees) {
            if (employee.getName().equals(name)) {
                this.employees.remove(employee);
                return true;
            }
        }
        return false;
    }

    public Employee getOldestEmployee() {
        Employee employee = null;
        int oldestEmployee = Integer.MIN_VALUE;
        for (Employee currentEmployee : this.employees) {
            if (currentEmployee.getAge() > oldestEmployee) {
                oldestEmployee = currentEmployee.getAge();
                employee = currentEmployee;
            }
        }
        return employee;
    }

    public Employee getEmployee(String name) {
        Employee employee = null;
        for (Employee currentEmployee : this.employees) {
            if (currentEmployee.getName().equals(name)) {
                employee = currentEmployee;
                break;
            }
        }
        return employee;
    }

    public int getCount() {
        return this.employees.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Employees working at Cafe %s\n", this.name));
                for(Employee employee : this.employees) {
                    sb.append(employee.toString()).append(System.lineSeparator());
                }
        return sb.toString();
    }
}
