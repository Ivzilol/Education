package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ImportTownDTO {


    @Size(min = 2, max = 19)
    private String townName;
    @Positive
    private int population;


    public ImportTownDTO() {
    }

    public String getTownName() {
        return townName;
    }

    public int getPopulation() {
        return population;
    }
}
