package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownSeedDto {
    @Expose
    @Size(min = 2)
    private String name;
    @Expose
    @Positive
    private Integer population;
    @Expose
    private String guide;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}
