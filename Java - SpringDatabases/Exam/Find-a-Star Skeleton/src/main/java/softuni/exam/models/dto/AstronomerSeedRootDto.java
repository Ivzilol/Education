package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "astronomers")
@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomerSeedRootDto {

    @XmlElement(name = "astronomer")
    private List<AstronomerSeedDto> astronomerSeedDtoList;

    public List<AstronomerSeedDto> getAstronomerSeedDtoList() {
        return astronomerSeedDtoList;
    }

    public void setAstronomerSeedDtoList(List<AstronomerSeedDto> astronomerSeedDtoList) {
        this.astronomerSeedDtoList = astronomerSeedDtoList;
    }
}
