package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.ExportForecastDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {


    Forecast findAllByCityAndDayOfWeek(Optional<City> city, DayOfWeek dayOfWeek);

    @Query("select new softuni.exam.models.dto.ExportForecastDto(" +
            "c.cityName, f.maxTemperature, f.minTemperature, f.sunset, f.sunrise as sunrise) " +
            " from City as c" +
            " join Forecast as f on c.id = f.city.id" +
            " where f.dayOfWeek = 'sunday' and c.population < 150000" +
            " order by f.maxTemperature desc , f.id")
    List<ExportForecastDto> exportSundayForecastWithCitizens();
}
