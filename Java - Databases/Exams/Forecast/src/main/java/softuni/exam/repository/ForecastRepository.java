package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.enyms.DaysOfWeek;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {


    Forecast findAllByCityAndDays(Optional<City> cityById, DaysOfWeek daysOfWeek);

    Set<Forecast> findAllByDaysAndCity_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DaysOfWeek dayOfWeek, Integer city_population);
}
