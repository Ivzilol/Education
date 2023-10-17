package com.example.BattleShips.repository;

import com.example.BattleShips.model.dto.ShipsDTO;
import com.example.BattleShips.model.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    Optional<Ship> findByName(String value);
    @Query("select new com.example.BattleShips.model.dto.ShipsDTO(" +
            "s.id, s.name as name, s.health, s.power)" +
            " from Ship as s" +
            " where s.user.id = :id")
    Set<ShipsDTO> findShipsCurrentUser(Long id);

    @Query("select new com.example.BattleShips.model.dto.ShipsDTO(" +
            "s.id, s.name as name, s.health, s.power)" +
            " from Ship as s" +
            " where s.user.id <> :id")
    Set<ShipsDTO> findEnemyShips(Long id);
    @Query("select s from Ship as s" +
            " where s.id = :id")
    Ship findShipById(Long id);
}
