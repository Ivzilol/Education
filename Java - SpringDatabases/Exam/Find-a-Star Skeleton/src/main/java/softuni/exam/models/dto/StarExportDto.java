package softuni.exam.models.dto;

public class StarExportDto {

    private String name;

    private Double lYears;

    private String description;

    private String nameConst;

    public StarExportDto(String name, Double lYears, String description, String nameConst) {
        this.name = name;
        this.lYears = lYears;
        this.description = description;
        this.nameConst = nameConst;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getlYears() {
        return lYears;
    }

    public void setlYears(Double lYears) {
        this.lYears = lYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameConst() {
        return nameConst;
    }

    public void setNameConst(String nameConst) {
        this.nameConst = nameConst;
    }
}
