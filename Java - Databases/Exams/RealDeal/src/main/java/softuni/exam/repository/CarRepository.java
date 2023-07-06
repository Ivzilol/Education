package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.ExportCarsDto;
import softuni.exam.models.entity.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findCarByMakeAndModelAndKilometers(String make, String model, Integer kilometers);

    @Query("select new softuni.exam.models.dto.ExportCarsDto(" +
            "c.make, c.model, c.kilometers, c.registeredOn, count(p.car.id) as countPicture) " +
            " from Car as c" +
            " join Picture as p on c.id = p.car.id" +
            " group by c.registeredOn" +
            " order by countPicture desc, c.make")
    List<ExportCarsDto> exportCars();
}
