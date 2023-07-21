package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {


    Optional<Player> findByEmail(String email);

//    @Query("select p.firstName, p.lastName, p.position, t.name, t.stadiumName from Player as p" +
//            " join Team as t on p.team.id = t.id" +
//            " where YEAR(p.birthDate) > 1994 and YEAR(p.birthDate) < 2004")
//    List<Player> findPlayerBirthDate();

    List<Player> findByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(LocalDate after, LocalDate before);
}
