package com.example.BattleShips.controller;

import com.example.BattleShips.model.dto.BattleDTO;
import com.example.BattleShips.model.dto.ShipsDTO;
import com.example.BattleShips.service.ShipService;
import com.example.BattleShips.service.UserService;
import com.example.BattleShips.util.LoggedUser;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("")
public class HomeController {

    private final LoggedUser loggedUser;

    private final UserService userService;

    private final ShipService shipService;


    public HomeController(LoggedUser loggedUser, UserService userService, ShipService shipService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.shipService = shipService;
    }

    @GetMapping("")
    public String home() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        Set<ShipsDTO> userShipsDTO = this.shipService.findShipsCurrentUser(loggedUser.getId());
        model.addAttribute("userShipsDTO", userShipsDTO);
        Set<ShipsDTO> enemyShipsDTO = this.shipService.findEnemyShips(loggedUser.getId());
        model.addAttribute("enemyShipsDTO", enemyShipsDTO);
        return "home";
    }

    @PostMapping("/fire")
    public String battle(@Valid BattleDTO battleDTO) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.shipService.battle(loggedUser.getId(), battleDTO);
        return "redirect:/home";
    }

    @ModelAttribute
    public BattleDTO battleDTO() {
        return new BattleDTO();
    }


    @GetMapping("/logout")
    String logout() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        this.userService.logout();
        return "redirect:/";
    }
}
