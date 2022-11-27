package softuni.exam.models.dto;

import softuni.exam.models.entity.DayOfWeek;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportForecastDTO {

    @XmlElement(name = "day_of_week")
    @NotNull
    private DayOfWeek dayOfWeek;

    @XmlElement(name = "max_temperature")
    @DecimalMin(value = "-20")
    @DecimalMax(value = "60")
    @NotNull
    private Double maxTemperature;

    @XmlElement(name = "min_temperature")
    @DecimalMin(value = "-50")
    @DecimalMax(value = "40")
    @NotNull
    private Double minTemperature;

    @XmlElement(name = "sunrise")
    private String sunrise;

    @XmlElement(name = "sunset")
    private String sunset;

    @XmlElement
    private Long city;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public ImportForecastDTO setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public ImportForecastDTO setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
        return this;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public ImportForecastDTO setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
        return this;
    }
    @NotNull
    public String getSunrise() {
        return sunrise;
    }

    public ImportForecastDTO setSunrise(String sunrise) {
        this.sunrise = sunrise;
        return this;
    }
    @NotNull
    public String getSunset() {
        return sunset;
    }

    public ImportForecastDTO setSunset(String sunset) {
        this.sunset = sunset;
        return this;
    }

    public Long getCity() {
        return city;
    }

    public ImportForecastDTO setCity(Long city) {
        this.city = city;
        return this;
    }
}
