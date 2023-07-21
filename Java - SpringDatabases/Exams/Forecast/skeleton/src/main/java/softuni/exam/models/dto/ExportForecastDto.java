package softuni.exam.models.dto;

import java.time.LocalTime;

public class ExportForecastDto {

    private String cityName;

    private Double maxTemperature;

    private Double minTemperature;

    private LocalTime sunset;

    private LocalTime sunrise;

    public ExportForecastDto(String cityName, Double maxTemperature, Double minTemperature, LocalTime sunset, LocalTime sunrise) {
        this.cityName = cityName;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.sunset = sunset;
        this.sunrise = sunrise;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }
}
