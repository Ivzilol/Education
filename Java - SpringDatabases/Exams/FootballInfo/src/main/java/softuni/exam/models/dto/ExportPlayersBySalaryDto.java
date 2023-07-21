package softuni.exam.models.dto;

import java.math.BigDecimal;

public class ExportPlayersBySalaryDto {

    private String firstName;

    private String lastName;

    private Integer number;

    private BigDecimal salary;

    private String teamName;

    public ExportPlayersBySalaryDto(String firstName, String lastName, Integer number, BigDecimal salary, String teamName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.salary = salary;
        this.teamName = teamName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
