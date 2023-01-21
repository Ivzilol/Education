package exam.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportShopsDTO {

    @XmlElement(name = "address")
    @Size(min = 4)
    private String address;

    @XmlElement(name = "employee-count")
    @Min(1)
    @Max(50)
    private int employeeCount;

    @XmlElement(name = "income")
    @Min(20000)
    private BigDecimal income;

    @XmlElement(name = "name")
    @Size(min = 4)
    private String name;

    @XmlElement(name = "shop-area")
    @Min(150)
    private int shopArea;

    @XmlElement(name = "town")
    private TownNameDTO town;

    public String getAddress() {
        return address;
    }

    public ImportShopsDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public ImportShopsDTO setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
        return this;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public ImportShopsDTO setIncome(BigDecimal income) {
        this.income = income;
        return this;
    }

    public String getName() {
        return name;
    }

    public ImportShopsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getShopArea() {
        return shopArea;
    }

    public ImportShopsDTO setShopArea(int shopArea) {
        this.shopArea = shopArea;
        return this;
    }

    public TownNameDTO getTown() {
        return town;
    }

    public ImportShopsDTO setTown(TownNameDTO town) {
        this.town = town;
        return this;
    }
}
