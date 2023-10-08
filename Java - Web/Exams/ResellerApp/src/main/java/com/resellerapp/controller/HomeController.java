package com.resellerapp.controller;

import com.resellerapp.model.entity.dto.BoughtOfferDTO;
import com.resellerapp.model.entity.dto.OfferOtherUserDTO;
import com.resellerapp.model.entity.dto.OffersCurrentUserDTO;
import com.resellerapp.model.entity.entity.Offer;
import com.resellerapp.model.entity.entity.User;
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
        Optional<User> user = userService.findCurrentUser(loggedUser.getId());
        model.addAttribute("currentUserInfo", user);
        Set<OffersCurrentUserDTO> offersCurrentUserDTO = this.offerService.findAllOffersCurrentUser(loggedUser.getId());
        model.addAttribute("userOffer", offersCurrentUserDTO);
        Set<OfferOtherUserDTO> offerOtherUserDTO = this.offerService.findOfferOtherUsers(loggedUser.getId());
        model.addAttribute("otherUserOffer", offerOtherUserDTO);
        Set<BoughtOfferDTO> boughtOfferUser = this.offerService.findBoughtOfferCurrentUser(loggedUser.getId());
        model.addAttribute("boughtOfferUser", boughtOfferUser);
        return "home";
    }

    @GetMapping("/remove/{id}")
    public String removeOffer(@PathVariable Long id){
        this.offerService.removeOffer(id);
        return "redirect:/home";
    }

    @GetMapping("bought-offer/{id}")
    public String boughtOffer(@PathVariable Long id){
        offerService.boughtOffer(id, loggedUser.getId());
        return "redirect:/home";
    }
}
