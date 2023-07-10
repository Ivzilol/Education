package softuni.exam.models.dto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamSeedDto {

    @XmlElement(name = "name")
    @Size(min = 3, max = 20)
    private String name;

    @XmlElement(name = "picture")
    private PictureUrl pictureUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureUrl getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(PictureUrl pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
