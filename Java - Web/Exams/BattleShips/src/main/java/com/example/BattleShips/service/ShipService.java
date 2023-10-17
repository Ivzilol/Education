package com.example.BattleShips.service;

import com.example.BattleShips.model.dto.AddShipDTO;
import com.example.BattleShips.model.dto.BattleDTO;
import com.example.BattleShips.model.dto.ShipsDTO;
import com.example.BattleShips.model.entity.Category;
import com.example.BattleShips.model.entity.Ship;
import com.example.BattleShips.model.entity.User;
import com.example.BattleShips.repository.CategoryRepository;
import com.example.BattleShips.repository.ShipRepository;
import com.example.BattleShips.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ShipService {

    private final ShipRepository shipRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    public ShipService(ShipRepository shipRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.shipRepository = shipRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Optional<Ship> findShipByName(String value) {
        return this.shipRepository.findByName(value);
    }

    public void addShip(AddShipDTO addShipDTO, Long userId) {
        Ship ship = new Ship();
        ship.setName(addShipDTO.getName());
        ship.setPower(addShipDTO.getPower());
        ship.setHealth(addShipDTO.getHealth());
        Optional<User> user = this.userRepository.findById(userId);
        ship.setUser(user.get());
        Category category = this.categoryRepository.findCategory(addShipDTO.getCategory());
        ship.setCategory(category);
        this.shipRepository.save(ship);
    }

    public Set<ShipsDTO> findShipsCurrentUser(Long id) {
        return this.shipRepository.findShipsCurrentUser(id);
    }


    public Set<ShipsDTO> findEnemyShips(Long id) {
        return this.shipRepository.findEnemyShips(id);
    }


    public void battle(Long id, BattleDTO battleDTO) {
        Ship userShip = this.shipRepository.findShipById(battleDTO.getUserShipId());
        Ship enemyShip = this.shipRepository.findShipById(battleDTO.getEnemyShipId());
        int healthEnemyShip = enemyShip.getHealth() - userShip.getPower();
        if (healthEnemyShip <= 0) {
            this.shipRepository.delete(enemyShip);
        } else {
            enemyShip.setHealth(healthEnemyShip);
            this.shipRepository.save(enemyShip);
        }
    }
}
