package hiberspring.models.dtos;

public class ExportEmployeeDto {

    private String fullName;

    private String position;

    private String number;

    public ExportEmployeeDto(String fullName, String position, String number) {
        this.fullName = fullName;
        this.position = position;
        this.number = number;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
