package softuni.exam.models.dto;

import softuni.exam.models.entity.CarType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedDto {

    @XmlElement(name = "carMake")
    @Size(min = 2 ,max = 30)
    private String carMake;

    @XmlElement(name = "carModel")
    @Size(min = 2 ,max = 30)
    private String carModel;

    @XmlElement(name = "year")
    @Positive
    private Integer year;

    @XmlElement(name = "plateNumber")
    @Size(min = 2 ,max = 30)
    private String plateNumber;

    @XmlElement(name = "kilometers")
    @Positive
    private Integer kilometers;

    @XmlElement(name = "engine")
    @Min(1)
    private Double engine;

    @XmlElement(name = "carType")
    private CarType carType;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public Double getEngine() {
        return engine;
    }

    public void setEngine(Double engine) {
        this.engine = engine;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
