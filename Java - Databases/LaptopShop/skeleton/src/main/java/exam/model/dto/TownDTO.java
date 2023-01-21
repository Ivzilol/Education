package exam.model.dto;

import javax.validation.constraints.Size;

public class TownDTO {

    @Size(min = 2)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
