package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamSeedRootDto {

    @XmlElement(name = "team")
    private List<TeamSeedDto> teamSeedDtoList;

    public List<TeamSeedDto> getTeamSeedDtoList() {
        return teamSeedDtoList;
    }

    public void setTeamSeedDtoList(List<TeamSeedDto> teamSeedDtoList) {
        this.teamSeedDtoList = teamSeedDtoList;
    }
}
