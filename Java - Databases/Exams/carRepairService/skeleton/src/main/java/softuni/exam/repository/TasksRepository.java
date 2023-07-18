package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.ExportPricedTasksDto;
import softuni.exam.models.entity.Task;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {

    @Query("select new softuni.exam.models.dto.ExportPricedTasksDto(" +
            "t.id, t.price, c.carMake, c.carModel, c.engine, concat(m.firstName, ' ', m.lastName) as fullName, c.kilometers) " +
            " from Task as t" +
            " join Car as c on c.id = t.cars.id" +
            " join Mechanic as m on m.id = t.mechanic.id" +
            " where c.carType = 'coupe'" +
            " order by t.price desc")
    List<ExportPricedTasksDto> findPricedTasks();
}
