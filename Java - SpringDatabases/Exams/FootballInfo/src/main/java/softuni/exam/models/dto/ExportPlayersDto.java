package softuni.exam.models.dto;

public class ExportPlayersDto {

    private String firstName;

    private String lastName;

    private String position;

    private Integer number;

    private String teamName;

    public ExportPlayersDto(String firstName, String lastName, String position, Integer number, String teamName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
