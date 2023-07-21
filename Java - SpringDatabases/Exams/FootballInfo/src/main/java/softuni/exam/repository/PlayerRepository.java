package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.ExportPlayersBySalaryDto;
import softuni.exam.models.dto.ExportPlayersDto;
import softuni.exam.models.entity.Player;

import java.util.List;

@Repository
public interface PlayerRepository  extends JpaRepository<Player, Long> {

    @Query("select new softuni.exam.models.dto.ExportPlayersDto(" +
            "p.firstName, p.lastName, p.position, p.number, t.name as teamName)" +
            " from Player as p" +
            " join Team as t on t.id = p.team.id" +
            " where t.name = 'North Hub'" +
            " order by p.id")
    List<ExportPlayersDto> findAllPlayers();
    @Query("select new softuni.exam.models.dto.ExportPlayersBySalaryDto(" +
            "p.firstName, p.lastName, p.number, p.salary, t.name as teamName)" +
            " from Player as p" +
            " join Team as t on t.id = p.team.id" +
            " where p.salary > 100000" +
            " order by p.salary desc")
    List<ExportPlayersBySalaryDto> findBySalary();
}
