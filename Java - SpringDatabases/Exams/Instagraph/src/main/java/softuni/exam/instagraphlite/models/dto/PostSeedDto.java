package softuni.exam.instagraphlite.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedDto {

    @XmlElement(name = "caption")
    @Size(min = 21)
    @NotNull
    private String caption;

    @XmlElement(name = "user")
    @NotNull
    private UserUsername userUsername;

    @XmlElement(name = "picture")
    @NotNull
    private PicturePath picturePate;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public UserUsername getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(UserUsername userUsername) {
        this.userUsername = userUsername;
    }

    public PicturePath getPicturePate() {
        return picturePate;
    }

    public void setPicturePate(PicturePath picturePate) {
        this.picturePate = picturePate;
    }
}
