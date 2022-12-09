package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOffersDTO {

    @XmlElement(name = "price")
    private BigDecimal price;

    @XmlElement(name = "agent")
    private AgentName name;

    @XmlElement(name = "apartment")
    private ApartmentIdDTO apartment;

    @XmlElement(name = "publishedOn")
    private String publishedOn;

    public BigDecimal getPrice() {
        return price;
    }

    public ImportOffersDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public AgentName getName() {
        return name;
    }

    public ImportOffersDTO setName(AgentName name) {
        this.name = name;
        return this;
    }

    public ApartmentIdDTO getApartment() {
        return apartment;
    }

    public ImportOffersDTO setApartment(ApartmentIdDTO apartment) {
        this.apartment = apartment;
        return this;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public ImportOffersDTO setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
        return this;
    }
}
