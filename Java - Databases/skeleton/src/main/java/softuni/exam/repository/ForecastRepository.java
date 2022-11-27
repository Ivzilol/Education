package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {


    Optional<Forecast> findByDayOfWeekAndMaxTemperatureAndMinTemperatureAndSunriseAndSunset(
            DayOfWeek dayOfWeek,
            Double maxTemperature,
            Double minTemperature,
            String sunrise, String sunset);

    Set<Forecast> findAllByDayOfWeekAndCities_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DayOfWeek dayOfWeek, int cities_population);
}
