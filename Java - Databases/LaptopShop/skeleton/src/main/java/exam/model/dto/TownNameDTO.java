package exam.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TownNameDTO {

    @XmlElement(name = "name")
    private String name;


    public String getName() {
        return name;
    }

    public TownNameDTO setName(String name) {
        this.name = name;
        return this;
    }
}
