package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.dto.ExportPictureDto;
import softuni.exam.instagraphlite.models.entity.Pictures;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PictureRepository extends JpaRepository<Pictures, Long> {

    Optional<Pictures> findByPath(String path);

//    @Query("select new softuni.exam.instagraphlite.models.dto.ExportPictureDto(" +
//            "p.path, p.size from Pictures as p)" +
//            " where p.size > :over" +
//            " order by p.size asc")
//    List<ExportPictureDto> findOver(Double over);
}
