package hiberspring.models.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownSeedDto {

    @Expose
    @NotNull
    private String name;
    @Expose
    @NotNull
    private Integer population;


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
}
