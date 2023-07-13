package hiberspring.models.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class BranchSeedDto {

    @Expose
    @NotNull
    private String name;
    @Expose
    @NotNull
    private String town;

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
