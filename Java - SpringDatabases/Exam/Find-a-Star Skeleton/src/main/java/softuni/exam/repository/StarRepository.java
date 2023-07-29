package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.StarExportDto;
import softuni.exam.models.entity.Star;

import java.util.List;
import java.util.Optional;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Star> findByName(String name);

    @Query("select new softuni.exam.models.dto.StarExportDto(" +
            "s.name, s.lightYears as lYears, s.description, c.name as nameConst)" +
            " from Star as s" +
            " left join Astronomer as a on s.id = a.observingStar.id" +
            " join Constellation as c on c.id = s.constellation.id" +
            " where s.starType = 'RED_GIANT' and a.observingStar.id is null" +
            " order by s.lightYears")
    List<StarExportDto> findStarRedGiant();
}
