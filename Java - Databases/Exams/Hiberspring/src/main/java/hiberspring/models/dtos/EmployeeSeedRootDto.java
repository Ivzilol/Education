package hiberspring.models.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeSeedRootDto {

    @XmlElement(name = "employee")
    private List<EmployeeSeedDto> employeeSeedDtoList;

    public List<EmployeeSeedDto> getEmployeeSeedDtoList() {
        return employeeSeedDtoList;
    }

    public void setEmployeeSeedDtoList(List<EmployeeSeedDto> employeeSeedDtoList) {
        this.employeeSeedDtoList = employeeSeedDtoList;
    }
}
