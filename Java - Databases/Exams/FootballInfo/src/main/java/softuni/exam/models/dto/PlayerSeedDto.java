package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class PlayerSeedDto {

    @Expose
    private String firstName;

    @Expose
    @Size(min = 3, max = 20)
    private String lastName;
    @Expose
    @Min(1)
    @Max(99)
    private Integer number;
    @Expose
    @Min(0)
    private BigDecimal salary;

    @Expose
    @Size(min = 2, max = 2)
    private String position;
    @Expose
    private PictureUrlDto picture;
    @Expose
    private TeamNameDto team;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public PictureUrlDto getPicture() {
        return picture;
    }

    public void setPicture(PictureUrlDto picture) {
        this.picture = picture;
    }

    public TeamNameDto getTeam() {
        return team;
    }

    public void setTeam(TeamNameDto team) {
        this.team = team;
    }
}
