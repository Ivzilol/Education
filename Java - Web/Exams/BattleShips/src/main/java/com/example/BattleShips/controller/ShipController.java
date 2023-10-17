package com.example.BattleShips.controller;

import com.example.BattleShips.model.dto.AddShipDTO;
import com.example.BattleShips.service.ShipService;
import com.example.BattleShips.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/ships")
public class ShipController {

    private final LoggedUser loggedUser;

    private final ShipService shipService;

    public ShipController(LoggedUser loggedUser, ShipService shipService) {
        this.loggedUser = loggedUser;
        this.shipService = shipService;
    }

    @GetMapping("/add-ship")
    public String addShip() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        return "ship-add";
    }

    @PostMapping("/add-ship")
    public String userAddShip(@Valid AddShipDTO addShipDTO,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addShipDTO", addShipDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addShipDTO", result);
            return "redirect:/ships/add-ship";
        }
        this.shipService.addShip(addShipDTO, loggedUser.getId());
        return "redirect:/home";
    }

    @ModelAttribute
    public AddShipDTO addShipDTO() {
        return new AddShipDTO();
    }
}
