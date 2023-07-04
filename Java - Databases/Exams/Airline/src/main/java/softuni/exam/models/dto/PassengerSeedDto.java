package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.springframework.context.annotation.Primary;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PassengerSeedDto {
    @Expose
    @Size(min = 2)
    private String firstName;
    @Expose
    @Size(min = 2)
    private String lastName;
    @Expose
    @Positive
    private Integer age;

    @Expose
    private String phoneNumber;
    @Expose
    @Email
    private String email;
    @Expose
    private String town;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
