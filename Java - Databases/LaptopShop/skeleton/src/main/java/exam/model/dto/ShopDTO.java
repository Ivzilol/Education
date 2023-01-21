package exam.model.dto;

import javax.validation.constraints.Size;

public class ShopDTO {

    @Size(min = 2)
    private String name;

    public ShopDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
