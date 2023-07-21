package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

public class PictureSeedDto {
    @Expose
    @Size(min = 2, max = 20)
    private String name;
    @Expose
    private String dateAndTime;
    @Expose
    private Long car;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }
}
