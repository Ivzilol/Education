package softuni.exam.instagraphlite.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedRootDto {

    @XmlElement(name = "post")
    private List<PostSeedDto> postSeedDtoList;

    public List<PostSeedDto> getPostSeedDtoList() {
        return postSeedDtoList;
    }

    public void setPostSeedDtoList(List<PostSeedDto> postSeedDtoList) {
        this.postSeedDtoList = postSeedDtoList;
    }
}
