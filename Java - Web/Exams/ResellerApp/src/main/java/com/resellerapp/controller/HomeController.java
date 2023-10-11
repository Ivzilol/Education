package com.resellerapp.controller;

import com.resellerapp.model.dto.BoughtOfferCurrentUser;
import com.resellerapp.model.dto.CurrentUserDTO;
import com.resellerapp.model.dto.CurrentUserOffersDTO;
import com.resellerapp.model.dto.OtherUsersOffersDTO;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(name = "/")
public class HomeController {

    private final LoggedUser loggedUser;

    private final UserService userService;

    private final OfferService offerService;

    public HomeController(LoggedUser loggedUser, UserService userService, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.offerService = offerService;
    }

    @GetMapping("")
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        Optional<CurrentUserDTO> currentUserDTO = this.userService.findCurrentUser(loggedUser.getId());
        model.addAttribute("currentUserDTO", currentUserDTO);
        Set<CurrentUserOffersDTO> currentUserOffersDTOS = this.offerService.getOffersCurrentUser(loggedUser.getId());
        model.addAttribute("currentUserOffersDTOS", currentUserOffersDTOS);
        Set<OtherUsersOffersDTO> otherUsersOffersDTO = this.offerService.getOtherUsersOffers(loggedUser.getId());
        model.addAttribute("otherUsersOffersDTO", otherUsersOffersDTO);
        Set<BoughtOfferCurrentUser> boughtOfferCurrentUsers = this.offerService.getBoughtOfferCurrentUser(loggedUser.getId());
        model.addAttribute("boughtOfferCurrentUsers", boughtOfferCurrentUsers);
        return "home";
    }

    @GetMapping("bought-offer/{id}")
    public String boughtOffer(@PathVariable Long id) {
        this.offerService.boughtOffer(id, loggedUser.getId());
        return "redirect:/home";
    }

    @GetMapping("delete/{id}")
    public String deleteOffer(@PathVariable Long id){
        this.offerService.deleteOffer(id, loggedUser.getId());
        return "redirect:/home";
    }
}
