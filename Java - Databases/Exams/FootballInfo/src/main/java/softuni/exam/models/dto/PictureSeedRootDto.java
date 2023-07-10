package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureSeedRootDto {

    @XmlElement(name = "picture")
    private List<PictureSeedDto> pictureSeedDtoList;


    public List<PictureSeedDto> getPictureSeedDtoList() {
        return pictureSeedDtoList;
    }

    public void setPictureSeedDtoList(List<PictureSeedDto> pictureSeedDtoList) {
        this.pictureSeedDtoList = pictureSeedDtoList;
    }
}
