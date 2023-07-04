package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedDto {

    @XmlElement(name = "serial-number")
    @Size(min = 2)
    private String serialNumber;

    @XmlElement(name = "price")
    @Positive
    private BigDecimal price;

    @XmlElement(name = "take-off")
    private String takeOff;

    @XmlElement(name = "from-town")
    private FromTown fromTown;

    @XmlElement(name = "to-town")
    private ToTown toTown;

    @XmlElement(name = "passenger")
    private PassengerEmail passengerEmail;

    @XmlElement(name = "plane")
    private PlaneRegisterNumber planeRegisterNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(String takeOff) {
        this.takeOff = takeOff;
    }

    public FromTown getFromTown() {
        return fromTown;
    }

    public void setFromTown(FromTown fromTown) {
        this.fromTown = fromTown;
    }

    public ToTown getToTown() {
        return toTown;
    }

    public void setToTown(ToTown toTown) {
        this.toTown = toTown;
    }

    public PassengerEmail getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(PassengerEmail passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public PlaneRegisterNumber getPlaneRegisterNumber() {
        return planeRegisterNumber;
    }

    public void setPlaneRegisterNumber(PlaneRegisterNumber planeRegisterNumber) {
        this.planeRegisterNumber = planeRegisterNumber;
    }
}
