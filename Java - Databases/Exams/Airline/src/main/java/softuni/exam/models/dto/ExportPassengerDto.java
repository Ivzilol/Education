package softuni.exam.models.dto;

public class ExportPassengerDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String serialNumber;

    private Long countTickets;

    public ExportPassengerDto(String firstName, String lastName, String email, String phoneNumber, String serialNumber, Long countTickets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.serialNumber = serialNumber;
        this.countTickets = countTickets;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getCountTickets() {
        return countTickets;
    }

    public void setCountTickets(Long countTickets) {
        this.countTickets = countTickets;
    }
}
