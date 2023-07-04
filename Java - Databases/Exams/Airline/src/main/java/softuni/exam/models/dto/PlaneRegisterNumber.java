package softuni.exam.models.dto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneRegisterNumber {

    @XmlElement(name = "register-number")
    @Size(min = 5)
    private String RegisterNumber;

    public String getRegisterNumber() {
        return RegisterNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        RegisterNumber = registerNumber;
    }
}
