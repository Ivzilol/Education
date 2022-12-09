package softuni.exam.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class ImportAgentDTO {

    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;


    private String town;
    @Email
    private String email;


    public ImportAgentDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTown() {
        return town;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
