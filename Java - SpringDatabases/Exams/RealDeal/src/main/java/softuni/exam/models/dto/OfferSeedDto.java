package softuni.exam.models.dto;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedDto {

    @XmlElement(name = "description")
    @Size(min = 5)
    private String description;

    @XmlElement(name = "price")
    @Positive
    private BigDecimal price;

    @XmlElement(name = "added-on")
    private String addedOn;

    @XmlElement(name = "has-gold-status")
    private boolean hasGoldStatus;

    @XmlElement(name = "car")
    private CarId car;

    @XmlElement(name = "seller")
    private SellerId seller;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public CarId getCar() {
        return car;
    }

    public void setCar(CarId car) {
        this.car = car;
    }

    public SellerId getSeller() {
        return seller;
    }

    public void setSeller(SellerId seller) {
        this.seller = seller;
    }
}
