package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.dto.ExportUsersWithPostDto;
import softuni.exam.instagraphlite.models.entity.Posts;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {


    @Query("select new softuni.exam.instagraphlite.models.dto.ExportUsersWithPostDto(" +
            "p.caption, p.user.id as userId, u.username, p2.size)" +
            " from Posts as p" +
            " join Users as u on u.id = p.user.id" +
            " join Pictures as p2 on p2.id = p.picture.id" +
            " order by userId")
    List<ExportUsersWithPostDto> findAllPost();


}
