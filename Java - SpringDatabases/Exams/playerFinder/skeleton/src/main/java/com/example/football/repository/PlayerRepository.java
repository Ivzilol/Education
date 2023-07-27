package com.example.football.repository;

import com.example.football.models.dto.ExportBestPlayersDto;
import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository  extends JpaRepository<Player, Long> {

    Optional<Player> findByEmail(String email);

    @Query("select new com.example.football.models.dto.ExportBestPlayersDto(" +
            "p.firstName, p.lastName, p.position, t.name as townName, t.stadiumName)" +
            " from Player as p" +
            " join Team as t on t.id = p.team.id" +
            " join Stat as s on s.id = p.stat.id" +
            " where year(p.birthDate) > 1994 and year(p.birthDate) < 2004" +
            " order by s.shooting desc, s.passing desc, s.endurance desc, p.lastName")
    List<ExportBestPlayersDto> exportBestPlayers();
}
