package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AgentSeedDto {
    @Expose
    @Size(min = 2)
    private String firstName;
    @Expose
    @Size(min = 2)
    private String lastName;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
