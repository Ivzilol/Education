package ExamPreparetion_01.bakery;

import java.util.ArrayList;
import java.util.Collection;

public class Bakery {

    private String name;
    private int capacity;
    private Collection<Employee> employees;

    public Bakery(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.employees = new ArrayList<>();
    }

    public void add(Employee employee) {
        if (this.employees.size() < this.capacity) {
            this.employees.add(employee);
        }
    }

    public boolean remove(String name) {
        for (Employee currentEmployee : this.employees) {
            if (currentEmployee.getName().equals(name)) {
                this.employees.remove(currentEmployee);
                return true;
            }
        }
        return false;
    }

    public Employee getOldestEmployee() {
        Employee employee = null;
        int oldest = Integer.MIN_VALUE;
        for (Employee employee1 : this.employees) {
            if (employee1.getAge() > oldest) {
                employee = employee1;
                oldest = employee1.getAge();
            }
        }
        return employee;
    }

    public Employee getEmployee(String name) {
        return this.employees.stream()
                .filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }

    public int getCount() {
        return this.employees.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Employees working at Bakery %s", name));
        sb.append(System.lineSeparator());
        for (Employee employee : this.employees) {
            sb.append(employee.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
