package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedRootDto {

    @XmlElement(name = "ticket")
    private List<TicketSeedDto> ticketSeedDtoList;


    public List<TicketSeedDto> getTicketSeedDtoList() {
        return ticketSeedDtoList;
    }

    public void setTicketSeedDtoList(List<TicketSeedDto> ticketSeedDtoList) {
        this.ticketSeedDtoList = ticketSeedDtoList;
    }
}
