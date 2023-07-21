package softuni.exam.models.dto;

import java.time.LocalDate;

public class ExportCarsDto {

    private String make;

    private String model;

    private Integer kilometers;

    private LocalDate registeredOn;

    private Long countPicture;

    public ExportCarsDto(String make, String model, Integer kilometers, LocalDate registeredOn, Long countPicture) {
        this.make = make;
        this.model = model;
        this.kilometers = kilometers;
        this.registeredOn = registeredOn;
        this.countPicture = countPicture;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Long getCountPicture() {
        return countPicture;
    }

    public void setCountPicture(Long countPicture) {
        this.countPicture = countPicture;
    }
}
