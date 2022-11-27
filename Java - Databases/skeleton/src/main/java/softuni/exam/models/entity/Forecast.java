package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private Double maxTemperature;


    @Column(nullable = false)
    private Double minTemperature;

    @Column(nullable = false)
    private String sunrise;

    @Column(nullable = false)
    private String sunset;

    @ManyToOne(optional = false)
    private City cities;

    public Forecast() {
    }

    public long getId() {
        return id;
    }

    public Forecast setId(long id) {
        this.id = id;
        return this;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Forecast setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public Forecast setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
        return this;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public Forecast setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
        return this;
    }

    public String getSunrise() {
        return sunrise;
    }

    public Forecast setSunrise(String sunrise) {
        this.sunrise = sunrise;
        return this;
    }

    public String getSunset() {
        return sunset;
    }

    public Forecast setSunset(String sunset) {
        this.sunset = sunset;
        return this;
    }

    public City getCities() {
        return cities;
    }

    public Forecast setCities(City cities) {
        this.cities = cities;
        return this;
    }

    @Override
    public String toString() {
        return dayOfWeek + "-" + maxTemperature;

    }
}
