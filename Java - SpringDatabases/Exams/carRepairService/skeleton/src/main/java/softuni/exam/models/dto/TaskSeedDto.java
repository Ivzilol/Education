package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskSeedDto {

    @XmlElement(name = "date")
    private String date;

    @XmlElement(name = "price")
    @Positive
    private BigDecimal price;

    @XmlElement(name = "car")
    @NotNull
    private Cars car;

    @XmlElement(name = "mechanic")
    @NotNull
    private MechanicFirstName mechanicFirstName;

    @XmlElement(name = "part")
    @NotNull
    private Parts part;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public MechanicFirstName getMechanicFirstName() {
        return mechanicFirstName;
    }

    public void setMechanicFirstName(MechanicFirstName mechanicFirstName) {
        this.mechanicFirstName = mechanicFirstName;
    }

    public Parts getPart() {
        return part;
    }

    public void setPart(Parts part) {
        this.part = part;
    }
}
