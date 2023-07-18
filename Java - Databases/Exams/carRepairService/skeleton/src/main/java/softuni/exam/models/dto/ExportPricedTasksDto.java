package softuni.exam.models.dto;


import java.math.BigDecimal;

public class ExportPricedTasksDto {

    private Long id;

    private BigDecimal price;

    private String carMake;

    private String carModel;

    private Double engine;

    private String fullName;

    private Integer kilometers;

    public ExportPricedTasksDto(Long id, BigDecimal price, String carMake, String carModel, Double engine, String fullName, Integer kilometers) {
        this.id = id;
        this.price = price;
        this.carMake = carMake;
        this.carModel = carModel;
        this.engine = engine;
        this.fullName = fullName;
        this.kilometers = kilometers;
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

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Double getEngine() {
        return engine;
    }

    public void setEngine(Double engine) {
        this.engine = engine;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }
}
