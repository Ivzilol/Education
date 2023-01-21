package exam.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ImportCustomersDTO {

    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    private String lastName;

    @Email
    private String email;


    private LocalDate registeredOn;

   private TownDTO town;


    public ImportCustomersDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public TownDTO getTown() {
        return town;
    }

    public void setTown(TownDTO town) {
        this.town = town;
    }
}
