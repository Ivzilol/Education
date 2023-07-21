package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedDto {

    @XmlElement(name = "price")
    @Positive
    private Double price;

    @XmlElement(name = "agent")
    private AgentName agentName;

    @XmlElement(name = "apartment")
    private ApartmentId apartmentId;

    @XmlElement(name = "publishedOn")
    private String publishedOn;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public AgentName getAgentName() {
        return agentName;
    }

    public void setAgentName(AgentName agentName) {
        this.agentName = agentName;
    }

    public ApartmentId getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(ApartmentId apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }
}
