package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneSeedRootDto {

    @XmlElement(name = "plane")
    private List<PlaneSeedDto> planeSeedDtoList;

    public List<PlaneSeedDto> getPlaneSeedDtoList() {
        return planeSeedDtoList;
    }

    public void setPlaneSeedDtoList(List<PlaneSeedDto> planeSeedDtoList) {
        this.planeSeedDtoList = planeSeedDtoList;
    }
}
