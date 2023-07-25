package softuni.exam.models.dto;

import java.math.BigDecimal;

public class ExportBestOffersDto {

    private String fullName;

    private Long id;

    private BigDecimal price;

    private Double area;

    private String townName;

    public ExportBestOffersDto(String fullName, Long id, BigDecimal price, Double area, String townName) {
        this.fullName = fullName;
        this.id = id;
        this.price = price;
        this.area = area;
        this.townName = townName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
