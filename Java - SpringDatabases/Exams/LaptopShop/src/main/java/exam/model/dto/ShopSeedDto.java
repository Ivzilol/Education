package exam.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopSeedDto {

    @XmlElement(name = "address")
    @Size(min = 4)
    private String address;

    @XmlElement(name = "employee-count")
    @Min(1)
    @Max(50)
    private Integer employeeCount;

    @XmlElement(name = "income")
    @Min(20000)
    private BigDecimal income;

    @XmlElement(name = "name")
    @Size(min = 4)
    private String name;

    @XmlElement(name = "shop-area")
    @Min(150)
    private Integer shopArea;

    @XmlElement(name = "town")
    private TownName townName;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShopArea() {
        return shopArea;
    }

    public void setShopArea(Integer shopArea) {
        this.shopArea = shopArea;
    }

    public TownName getTownName() {
        return townName;
    }

    public void setTownName(TownName townName) {
        this.townName = townName;
    }
}
